package com.auto.dealership.service.impl;

import com.auto.dealership.dto.ContactMessageRequest;
import com.auto.dealership.dto.ContactMessageResponse;
import com.auto.dealership.entity.ContactMessage;
import com.auto.dealership.mapper.ContactMessageMapper;
import com.auto.dealership.repository.ContactMessageRepository;
import com.auto.dealership.service.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactMessageServiceImpl implements ContactMessageService {
    private final ContactMessageMapper contactMessageMapper;
    private final ContactMessageRepository contactMessageRepository;

    @Override
    public void createContactMessage(ContactMessageRequest request) {
        ContactMessage contactMessage = contactMessageMapper.toEntity(request);
    }

    @Override
    public List<ContactMessageResponse> getAllContactMessages() {
        List<ContactMessage> contactMessages = contactMessageRepository.findAll();
        return contactMessages.stream()
                .sorted(Comparator
                        .comparing(ContactMessage::getCreatedAt).reversed())
                .map(contactMessageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteContactMessage(Long id) {
        contactMessageRepository.deleteById(id);
    }
}
