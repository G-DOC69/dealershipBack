package com.auto.dealership.mapper;

import com.auto.dealership.entity.Event;
import com.auto.dealership.dto.EventResponse;

public interface EventMapper {
    public Event toEntity();
    public EventResponse toDto(Event event);
}
