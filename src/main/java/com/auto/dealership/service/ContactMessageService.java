package com.auto.dealership.service;

import com.auto.dealership.dto.ContactMessageRequest;
import com.auto.dealership.dto.ContactMessageResponse;

import java.util.List;

public interface ContactMessageService {
    public void createContactMessage(ContactMessageRequest request);
    public List<ContactMessageResponse> getAllContactMessages();
    public void deleteContactMessage(Long id);
    public void deleteAllContactMessages();
}
