package com.aws.sqs.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.aws.sqs.model.Event;
import com.aws.sqs.repository.EventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueServiceImpl implements QueueService {

    private final AmazonSQSAsync amazonSQSAsync;

    private final ObjectMapper objectMapper;

    @Autowired
    private EventRepository eventRepository;


    public SendMessageResult sendSqsMessage(String queue, Object message) throws JsonProcessingException {
        String messageAsString = objectMapper.writeValueAsString(message);
        log.info("Writing message {} to queue {}", messageAsString, queue);

        //When connected to a real AWS account, only the queue name is required.
        SendMessageResult sendMessageResult = amazonSQSAsync.sendMessage("http://localhost:4566/000000000000/first-queue", messageAsString);


        return sendMessageResult;
    }






    public List<String> receiveMessage(String queue) {


        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest("http://localhost:4566/000000000000/first-queue")
                .withWaitTimeSeconds(15)
                .withVisibilityTimeout(15)
                .withMaxNumberOfMessages(10);
        ReceiveMessageResult messageResult = amazonSQSAsync.receiveMessage(receiveMessageRequest);



        List<Message> listOfMessage = amazonSQSAsync.receiveMessage(receiveMessageRequest).getMessages();
        List<String> messageIdList = new ArrayList<>();
        for(Message msg : listOfMessage) {


            Event event = new Event();
            event.setBody(msg.getBody());
            event.setAttributes(msg.getAttributes());
            event.setMessageAttributes(msg.getMessageAttributes());
            event.setReceiptHandler(msg.getReceiptHandle());
            event.setMd50OfMessageAttributes(msg.getMD5OfMessageAttributes());
            event.setMd50OfBody(msg.getMD5OfBody());


            messageIdList.add(eventRepository.saveEvent(event));

            System.out.println(event.toString());
        }

        return messageIdList;
    }

}
