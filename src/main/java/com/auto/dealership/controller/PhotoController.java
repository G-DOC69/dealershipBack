package com.auto.dealership.controller;

import com.auto.dealership.entity.Photo;
import com.auto.dealership.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoRepository photoRepo;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPhoto(@PathVariable Long id) {
        try {
            Photo photo = photoRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Photo not found"));

            System.out.println("→ photo ID: " + photo.getId());
            System.out.println("→ file name: " + photo.getFileName());
            System.out.println("→ mime type: '" + photo.getMimeType() + "'");
            System.out.println("→ byte size: " + (photo.getData() != null ? photo.getData().length : "null"));

            String safeMimeType = Optional.ofNullable(photo.getMimeType())
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .orElse("image/jpeg");

            MediaType mediaType = MediaType.parseMediaType(safeMimeType);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);
            headers.setContentDisposition(ContentDisposition.inline()
                    .filename(photo.getFileName())
                    .build());

            return new ResponseEntity<>(photo.getData(), headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Photo load failed: " + e.getMessage());
        }
    }

}
