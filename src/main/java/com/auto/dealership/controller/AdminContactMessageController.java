package com.auto.dealership.controller;

import com.auto.dealership.dto.ContactMessageResponse;
import com.auto.dealership.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/contact-message")
@RequiredArgsConstructor
public class AdminContactMessageController {

    private final ContactMessageService contactMessageService;

    @GetMapping("/all")
    public List<ContactMessageResponse> getAllMessages() {
        return contactMessageService.getAllContactMessages();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        contactMessageService.deleteContactMessage(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllMessages() {
        contactMessageService.deleteAllContactMessages();
        return ResponseEntity.ok().build();
    }
}
