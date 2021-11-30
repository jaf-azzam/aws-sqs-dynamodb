package com.aws.sqs.controller;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageResult;
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


    private final QueueService queueService;

    @PostMapping(path = "/first", produces = MediaType.APPLICATION_JSON_VALUE)
    public SendMessageResult sendMessageToFirstQueue(@RequestBody String message) throws JsonProcessingException {
        return queueService.sendSqsMessage("first-queue", message);
    }



    @GetMapping(path = "/getMessages", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getDataFromQueueOne() throws JsonProcessingException {
        return queueService.receiveMessage("queue-1");
    }
}
