package com.auto.dealership.controller;

import com.auto.dealership.dto.CarRequest;
import com.auto.dealership.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping("/cars")
    public ResponseEntity<Long> createCar(@RequestBody CarRequest request) {
        Long id = carService.createCar(request);
        return ResponseEntity.ok(id);
    }

}
