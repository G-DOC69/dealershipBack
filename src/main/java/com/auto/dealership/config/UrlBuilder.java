package com.auto.dealership.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class UrlBuilder {

    public String buildFullPhotoUrl(Long photoId) {
        if (photoId == null) return null;

        String baseUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build()
                .toUriString();

        return baseUrl + "/photos/" + photoId;
    }
}
