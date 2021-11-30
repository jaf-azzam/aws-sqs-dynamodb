package com.aws.sqs.listener;

import com.aws.sqs.model.Event;
import com.aws.sqs.model.MessageObject;
import com.aws.sqs.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueListener {


    /*
        Queue Listner
        Logs messages to console
     */
    @SqsListener(value = "http://localhost:4566/000000000000/first-queue", deletionPolicy = SqsMessageDeletionPolicy.ALWAYS)
    public void listenToFirstQueue(String message) {

        log.info("Received a message on first queue: {}", message);

    }


}
