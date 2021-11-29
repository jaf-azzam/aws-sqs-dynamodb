package com.aws.sqs.service;

import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface QueueService {
    SendMessageResult sendSqsMessage(String queue, Object message) throws JsonProcessingException;
//    ReceiveMessageResult receivedMessage(String queue) throws JsonProcessingException;

    List<Message> receiveMessage(String queue);
}
