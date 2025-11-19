package com.auto.dealership.controller;

import com.auto.dealership.dto.CarResponse;
import com.auto.dealership.dto.CarSingularResponse;
import com.auto.dealership.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/{id}")
    public CarSingularResponse getCarById(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @GetMapping("/all")
    public List<CarResponse> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/newest")
    public List<CarResponse> getNewestCars() {
        return carService.getNewestCars();
    }
}
