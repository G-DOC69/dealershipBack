package com.auto.dealership.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
    private Long id;

    private String brand;
    private String model;
    private Integer year;
    private BigDecimal price;
    private String color;

    private String photoUrl;
}
