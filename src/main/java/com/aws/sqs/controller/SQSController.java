package com.aws.sqs.controller;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.aws.sqs.config.QueueConfiguration;
import com.aws.sqs.listener.SampleListener;
import com.aws.sqs.model.MessageObject;
import com.aws.sqs.service.QueueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class SQSController {

    private final QueueConfiguration queueConfiguration;

    private final QueueService queueService;

    @PostMapping(path = "/first", produces = MediaType.APPLICATION_JSON_VALUE)
    public SendMessageResult sendMessageToFirstQueue(@RequestBody String message) throws JsonProcessingException {
        return queueService.sendSqsMessage(queueConfiguration.getFirstQueue(), message);
    }

    @PostMapping(path = "/second", produces = MediaType.APPLICATION_JSON_VALUE)
    public SendMessageResult sendMessageToSecondQueue(@RequestBody MessageObject message) throws JsonProcessingException {
        return queueService.sendSqsMessage(queueConfiguration.getSecondQueue(), message);
    }


    @GetMapping(path = "/getMessages", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> getDataFromQueueOne() throws JsonProcessingException {
        return queueService.receiveMessage("queue-1");
    }
}
