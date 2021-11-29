package com.aws.sqs.controller;

import com.aws.sqs.Repository.EventRepository;
import com.aws.sqs.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/saveEvent")
    public Event saveEvent (@RequestBody Event event)
    {
        return eventRepository.addevent(event);
    }

    @GetMapping("/findEvent/{eventId}")
    public Event findEvent (@PathVariable String eventId )
    {
        return eventRepository.findEventByEventId(eventId);
    }
    @DeleteMapping("/deleteEvent")
    public String deleteEvent (@PathVariable Event event)
    {
        return eventRepository.deleteevent(event);
    }


}
