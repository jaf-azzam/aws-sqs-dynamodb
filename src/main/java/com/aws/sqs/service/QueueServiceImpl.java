package com.aws.sqs.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueueServiceImpl implements QueueService {

    private final AmazonSQSAsync amazonSQSAsync;

    private final ObjectMapper objectMapper;

//    private SampleListener sampleListener = new SampleListener();

    public SendMessageResult sendSqsMessage(String queue, Object message) throws JsonProcessingException {
        String messageAsString = objectMapper.writeValueAsString(message);
        log.info("Writing message {} to queue {}", messageAsString, queue);

        //When connected to a real AWS account, only the queue name is required.
        SendMessageResult sendMessageResult = amazonSQSAsync.sendMessage("http://localhost:4566/queue/" + queue, messageAsString);


//        sampleListener.listenToFirstQueue(message.toString());
        return sendMessageResult;
    }






    public List<Message> receiveMessage(String queue) {

        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest("http://localhost:4566/queue/" + queue)
                .withWaitTimeSeconds(15)
                .withMaxNumberOfMessages(10);
        return amazonSQSAsync.receiveMessage(receiveMessageRequest).getMessages();
    }

}
