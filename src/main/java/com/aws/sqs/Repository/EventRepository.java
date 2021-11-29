package com.aws.sqs.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.aws.sqs.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class EventRepository {
     @Autowired
    private DynamoDBMapper  mapper;

     public Event  addevent(Event event)
     {
         mapper.save(event);
         return event;
     }
     public  Event getevent(String eventId)
     {
         return mapper.load(Event.class,eventId);
     }
    public  String deleteevent(Event event)
    {
        mapper.delete(event);
        return  "person removed!";
    }
    public  String updatevent(Event event)
    {
        mapper.save(event,buildExpression(event));
        return  "record updated...!";
    }
    private DynamoDBSaveExpression buildExpression(Event event)
    {
        DynamoDBSaveExpression dynamoDBSaveExpression=new DynamoDBSaveExpression();
        HashMap<String, ExpectedAttributeValue>expectedAMap=new HashMap<>();
        expectedAMap.put("eventId",new ExpectedAttributeValue(new AttributeValue().withS(event.getEventId())));
        dynamoDBSaveExpression.setExpected(expectedAMap);
        return dynamoDBSaveExpression;
    }

    public Event findEventByEventId(String eventId) {
        return mapper.load(Event.class,eventId);
    }
}
