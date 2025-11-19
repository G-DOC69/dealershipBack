package com.auto.dealership.service.impl;

import com.auto.dealership.entity.Car;
import com.auto.dealership.entity.Event;
import com.auto.dealership.entity.Photo;
import com.auto.dealership.repository.CarRepository;
import com.auto.dealership.repository.EventRepository;
import com.auto.dealership.repository.PhotoRepository;
import com.auto.dealership.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final CarRepository carRepository;
    private final EventRepository eventRepository;

    @Override
    public void replaceCarPhotos(Car car, List<MultipartFile> files) {
        List<Photo> existingPhotos = car.getPhotos();
        if (existingPhotos != null && !existingPhotos.isEmpty()) {
            for (Photo photo : new ArrayList<>(existingPhotos)) {
                deletePhoto(photo);
            }
            existingPhotos.clear();
        }
        for (MultipartFile file : files) {
            Photo photo = savePhotoToDatabase(file);
            photo.setCar(car);
            photoRepository.save(photo);
            existingPhotos.add(photo);
        }
        carRepository.save(car);
    }

    @Override
    public void replaceEventPhoto(Event event, MultipartFile file) {
        if (event.getPhoto() != null) {
            deletePhoto(event.getPhoto());
        }
        Photo newPhoto = savePhotoToDatabase(file);
        newPhoto.setEvent(event);
        event.setPhoto(photoRepository.save(newPhoto));
        eventRepository.save(event);
    }

    @Override
    public void appendCarPhoto(Car car, List<MultipartFile> files) {
        for (MultipartFile file : files) {
            Photo photo = savePhotoToDatabase(file);
            photo.setCar(car);
            photoRepository.save(photo);
            car.getPhotos().add(photo);
        }
        carRepository.save(car);
    }

    private void validateFile(MultipartFile file) {
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new IllegalArgumentException("Max file size is 10MB");
        }
        String type = file.getContentType();
        if (!List.of("image/jpeg", "image/png", "image/pdf").contains(type)) {
            throw new IllegalArgumentException("Only JPG, PNG, and PDF files are allowed");
        }
    }

    private Photo savePhotoToDatabase(MultipartFile file) {
        validateFile(file);
        try {
            Photo photo = new Photo();
            photo.setFileName(UUID.randomUUID() + "_" + file.getOriginalFilename());
            photo.setMimeType(file.getContentType());
            photo.setUploadTime(LocalDateTime.now());
            photo.setData(file.getBytes());
            return photo;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save photo to database", e);
        }
    }

    private void deletePhoto(Photo photo) {
        if (photo != null) {
            photoRepository.delete(photo);
        }
    }
}
