package com.auto.dealership.mapper;

import com.auto.dealership.dto.ContactMessageRequest;
import com.auto.dealership.dto.ContactMessageResponse;
import com.auto.dealership.entity.ContactMessage;

import java.util.List;

public interface ContactMessageMapper {
    public ContactMessage toEntity(ContactMessageRequest request);
    public ContactMessageResponse toDto(ContactMessage contactMessage);
    public List<ContactMessageResponse> toDtoList(List<ContactMessage> contactMessages);
}
