package com.auto.dealership.controller;

import com.auto.dealership.entity.Event;
import com.auto.dealership.service.EventService;
import com.auto.dealership.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/admin/event")
@RequiredArgsConstructor
public class AdminEventController {

    private final EventService eventService;
    private final PhotoService photoService;

    @PostMapping(value = "/create-or-replace", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createOrReplaceEvent(
            @RequestPart(value = "photo", required = false) MultipartFile photo
    ) throws Exception {
        Long eventId = eventService.createEvent();

        if (photo != null && !photo.isEmpty()) {
            Event event = eventService.getEventEntity(eventId);
            photoService.replaceEventPhoto(event, photo);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEvent() {
        eventService.deleteEvent();
        return ResponseEntity.ok().build();
    }
}
