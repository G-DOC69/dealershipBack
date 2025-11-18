package com.auto.dealership.service.impl;

import com.auto.dealership.entity.Event;
import com.auto.dealership.repository.EventRepository;
import com.auto.dealership.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public Long createEvent() {
        eventRepository.deleteAll();
        Event event = new Event();
        eventRepository.save(event);
        return event.getId();
    }

    @Override
    public void deleteEvent() {
    eventRepository.deleteAll();
    }
}
