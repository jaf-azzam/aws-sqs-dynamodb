package com.aws.sqs.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.aws.sqs.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class EventRepository {

    @Autowired
    private DynamoDBMapper mapper;

    public String saveEvent(Event event) {
        mapper.save(event);
        return event.getMessageId();
    }



    public Event findBymessageId(String messageId) {

        return mapper.load(Event.class, messageId);

    }

    public String deleteEvent(Event event) {
        mapper.delete(event);
        return "Deleted Successfully !";
    }

    public String updateEvent(Event event) {
        // buildExpression -> to check whether id exists or not
        mapper.save(event, buildExpression(event));
        return "Event Updated ! ";
    }

    private DynamoDBSaveExpression buildExpression(Event event) {
        DynamoDBSaveExpression dynamoDBSaveExpression  = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("messageId", new ExpectedAttributeValue(new AttributeValue().withS(event.getMessageId())));

        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }

}
