package com.auto.dealership.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarSingularResponse {

    private Long id;

    private String brand;
    private String model;
    private Integer mileage;
    private Integer year;
    private String color;

    private BigDecimal price;
    private String description;

    private LocalDate createdAt;
    private LocalDate lastModifiedAt;

    private List<String> photoUrls;
}
