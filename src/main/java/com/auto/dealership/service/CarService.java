package com.auto.dealership.service;

import com.auto.dealership.dto.CarRequest;
import com.auto.dealership.dto.CarResponse;
import com.auto.dealership.dto.CarSingularResponse;

import java.util.List;

public interface CarService {
    public CarSingularResponse getCar(Long id);
    public List<CarResponse> getAllCars();
    public List<CarResponse> getNewestCars();
    public Long createCar(CarRequest request);
    public void deleteCar(Long id);
}
