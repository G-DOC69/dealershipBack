package com.auto.dealership.mapper.impl;

import com.auto.dealership.config.UrlBuilder;
import com.auto.dealership.dto.CarRequest;
import com.auto.dealership.dto.CarResponse;
import com.auto.dealership.dto.CarSingularResponse;
import com.auto.dealership.entity.Car;
import com.auto.dealership.entity.Photo;
import com.auto.dealership.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CarMapperImpl implements CarMapper {
    private final UrlBuilder urlBuilder;

    @Override
    public Car toEntity(CarRequest request) {
        Car car = new Car();
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setPrice(request.getPrice());
        car.setPrice(request.getPrice());
        car.setDescription(request.getDescription());
        car.setColor(request.getColor());
        car.setMileage(request.getMileage());
        car.setAvailable(true);
        return car;
    }

    @Override
    public CarResponse toSimpleDto(Car car) {
        String firstPhoto = car.getPhotos().isEmpty() ? null :
                urlBuilder.buildFullPhotoUrl(car.getPhotos().getFirst().getId());

        return new CarResponse(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getPrice(),
                car.getColor(),
                firstPhoto
        );
    }

    @Override
    public CarSingularResponse toDto(Car car) {
        List<String> photoUrls = car.getPhotos().stream()
                .map(Photo::getId)
                .map(urlBuilder::buildFullPhotoUrl)
                .toList();
        return new CarSingularResponse(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getMileage(),
                car.getYear(),
                car.getColor(),
                car.getPrice(),
                car.getDescription(),
                car.getCreatedAt(),
                car.getLastModifiedAt(),
                photoUrls
        );
    }

    @Override
    public List<CarResponse> toSimpleDtoList(List<Car> cars) {
        return cars.stream().map(this::toSimpleDto).collect(Collectors.toList());
    }
}
