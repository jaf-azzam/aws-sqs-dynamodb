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
    public String addEvent(@RequestBody Event event) {
        return eventRepository.saveEvent(event);
    }

    @GetMapping("/get-event/{messageId}")
    public Event getEvent(@PathVariable String messageId) {
        return eventRepository.findBymessageId(messageId);
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
