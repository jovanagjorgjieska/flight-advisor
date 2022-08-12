package com.example.flightadvisor.model.dto;

import lombok.Getter;

@Getter
public class RouteDTO {
    private String sourceCode;

    private String destinationCode;

    private Double price;
}
