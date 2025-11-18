package com.auto.dealership.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageRequest {
    private String username;
    private String phone;
    private String message;
}
