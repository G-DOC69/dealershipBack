package com.auto.dealership.controller;

import com.auto.dealership.dto.CarRequest;
import com.auto.dealership.entity.Car;
import com.auto.dealership.service.CarService;
import com.auto.dealership.service.PhotoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/car")
@RequiredArgsConstructor
public class AdminCarController {

    private final CarService carService;
    private final PhotoService photoService;
    private final ObjectMapper objectMapper;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> createCar(
            @RequestPart("formData") String formDataJson,
            @RequestPart(value = "photos", required = false) List<MultipartFile> photos
    ) throws Exception {

        CarRequest request = objectMapper.readValue(formDataJson, CarRequest.class);
        Long carId = carService.createCar(request);

        if (photos != null && !photos.isEmpty()) {
            Car car = carService.getCarEntity(carId);
            photoService.replaceCarPhotos(car, photos);
        }

        return ResponseEntity.ok(carId);
    }

    @PutMapping(value = "/{carId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> updateCar(
            @PathVariable Long carId,
            @RequestPart("formData") String formDataJson,
            @RequestPart("replacePhotos") Boolean replacePhotos,
            @RequestPart(value = "photos", required = false) List<MultipartFile> photos
    ) throws Exception {

        CarRequest request = objectMapper.readValue(formDataJson, CarRequest.class);
        Long Id = carService.createCar(request);

        if (photos != null && !photos.isEmpty()) {
            if (Boolean.TRUE.equals(replacePhotos)) {
                Car car = carService.getCarEntity(Id);
                photoService.replaceCarPhotos(car, photos);
            } else {
                Car car = carService.getCarEntity(Id);
                photoService.appendCarPhoto(car, photos);
            }
        }

        return ResponseEntity.ok(carId);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.ok().build();
    }
}
