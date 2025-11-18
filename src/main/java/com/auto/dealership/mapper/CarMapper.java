package com.auto.dealership.mapper;


import com.auto.dealership.dto.CarRequest;
import com.auto.dealership.dto.CarResponse;
import com.auto.dealership.dto.CarSingularResponse;
import com.auto.dealership.entity.Car;

import java.util.List;


public interface CarMapper {
    public Car toEntity(CarRequest request);
    public CarResponse toSimpleDto(Car car);
    public CarSingularResponse toDto(Car car);
    public List<CarResponse> toSimpleDtoList(List<Car> cars);
}
