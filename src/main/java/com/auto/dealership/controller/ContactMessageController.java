package com.auto.dealership.controller;

import com.auto.dealership.service.ContactMessageService;
import com.auto.dealership.dto.ContactMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    @PostMapping
    public ResponseEntity<Void> createContactMessage(
            @RequestBody ContactMessageRequest request) {

        contactMessageService.createContactMessage(request);
        return ResponseEntity.ok().build();
    }
}
