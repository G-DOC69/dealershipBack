package com.auto.dealership.service.impl;

import com.auto.dealership.dto.EventResponse;
import com.auto.dealership.entity.Event;
import com.auto.dealership.mapper.EventMapper;
import com.auto.dealership.repository.EventRepository;
import com.auto.dealership.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

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

    @Override
    public Event getEventEntity(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
    }

    @Override
    public EventResponse getEvent() {
        Optional<Event> eventOptional = eventRepository.findFirstByOrderByIdAsc();

        if (eventOptional.isEmpty()) {
            return null;
        }

        Event event = eventOptional.get();
        return eventMapper.toDto(event);
    }

}
