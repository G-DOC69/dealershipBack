package com.auto.dealership.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageResponse {
    private Long id;
    private String username;
    private String phone;
    private String message;
    private LocalDateTime createdAt;
}
