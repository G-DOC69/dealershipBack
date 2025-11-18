package com.auto.dealership.service.impl;

import com.auto.dealership.dto.CarRequest;
import com.auto.dealership.dto.CarResponse;
import com.auto.dealership.dto.CarSingularResponse;
import com.auto.dealership.entity.Car;
import com.auto.dealership.mapper.CarMapper;
import com.auto.dealership.repository.CarRepository;
import com.auto.dealership.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepo;
    private final CarMapper carMapper;

    @Override
    public CarSingularResponse getCar(Long id) {
        Car car = carRepo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        return carMapper.toDto(car);
    }

    @Override
    public List<CarResponse> getAllCars() {
        List<Car> cars = carRepo.findAll();

        return cars.stream()
                .sorted(Comparator
                        .comparing(Car::getCreatedAt).reversed())
                .map(carMapper::toSimpleDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarResponse> getNewestCars() {
        return carMapper.toSimpleDtoList(carRepo.findTop3ByOrderByCreatedAtDesc());
    }

    @Override
    public Long createCar(CarRequest request) {
        Car car = carMapper.toEntity(request);
        carRepo.save(car);
        return car.getId();
    }


    @Override
    public void deleteCar(Long id) {
        Car car = carRepo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        carRepo.delete(car);
    }
}
