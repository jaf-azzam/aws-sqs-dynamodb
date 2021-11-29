package com.aws.sqs.repository;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.aws.sqs.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EventRepository {

    @Autowired
    private DynamoDBMapper mapper;

    public Event savEvent(Event event) {
        mapper.save(event);
        return event;
    }



    public Event findByEventId(String eventId) {
        return mapper.load(Event.class, eventId);
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
        expectedMap.put("eventId", new ExpectedAttributeValue(new AttributeValue().withS(event.getEventId())));

        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }

}
