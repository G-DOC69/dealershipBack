package com.auto.dealership.service;

import com.auto.dealership.dto.EventResponse;
import com.auto.dealership.entity.Event;

public interface EventService {
    public Long createEvent();
    public void deleteEvent();
    public Event getEventEntity(Long id);
    public EventResponse getEvent();
}
