package com.auto.dealership.mapper.impl;

import com.auto.dealership.dto.ContactMessageRequest;
import com.auto.dealership.dto.ContactMessageResponse;
import com.auto.dealership.entity.ContactMessage;
import com.auto.dealership.mapper.ContactMessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContactMessageMapperImpl implements ContactMessageMapper {
    @Override
    public ContactMessage toEntity(ContactMessageRequest request) {
        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setUsername(request.getUsername());
        contactMessage.setPhone(request.getPhone());
        contactMessage.setMessage(request.getMessage());
        return contactMessage;
    }

    @Override
    public ContactMessageResponse toDto(ContactMessage contactMessage) {
        ContactMessageResponse contactMessageResponse = new ContactMessageResponse();
        contactMessageResponse.setId(contactMessage.getId());
        contactMessageResponse.setUsername(contactMessage.getUsername());
        contactMessageResponse.setPhone(contactMessage.getPhone());
        contactMessageResponse.setMessage(contactMessage.getMessage());
        contactMessageResponse.setCreatedAt(contactMessage.getCreatedAt());
        return contactMessageResponse;
    }

    @Override
    public List<ContactMessageResponse> toDtoList(List<ContactMessage> contactMessages) {
        return contactMessages.stream().map(this::toDto).collect(Collectors.toList());
    }
}
