package com.aws.sqs.controller;


import com.aws.sqs.model.Event;
import com.aws.sqs.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dynamo")
public class DynamoDBController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String testAPI() {
        return "Working fine -- ";
    }

    @PostMapping("/add-event")
    public Event addEvent(@RequestBody Event event) {
        return eventRepository.savEvent(event);
    }

    @GetMapping("/get-event/{eventId}")
    public Event getEvent(@RequestParam String eventId) {
        return eventRepository.findByEventId(eventId);
    }

    @DeleteMapping("/delete-event")
    public String deleteEvent(@RequestBody Event event) {
        return eventRepository.deleteEvent(event);
    }

    @PutMapping("/update-event")
    public String updateEvent(@RequestBody Event event) {
        return eventRepository.updateEvent(event);
    }

}
