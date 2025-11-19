package com.auto.dealership.controller;

import com.auto.dealership.dto.EventResponse;
import com.auto.dealership.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    @GetMapping
    public EventResponse getEvent() {
        return eventService.getEvent();
    }
}
