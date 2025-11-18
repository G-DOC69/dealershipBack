package com.auto.dealership.mapper.impl;

import com.auto.dealership.config.UrlBuilder;
import com.auto.dealership.dto.EventResponse;
import com.auto.dealership.entity.Event;
import com.auto.dealership.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventMapperImpl implements EventMapper {

    private final UrlBuilder urlBuilder;

    @Override
    public Event toEntity() {
        return new Event();
    }

    @Override
    public EventResponse toDto(Event event) {
        String photoUrl = event.getPhoto() != null
                ? urlBuilder.buildFullPhotoUrl(event.getPhoto().getId())
                : null;

        return new EventResponse(event.getId(), photoUrl);
    }
}
