package com.example.flightadvisor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Route {

    private String airlineCode;

    @Id
    private String airlineId;

    private String sourceAirportCode;

    private String sourceAirportId;

    private String destinationAirportCode;

    private String destinationAirportId;

    private String codeshare;

    private Integer stops;

    private String equipment;

    private Double price;

    public Route(String airlineCode, String airlineId, String sourceAirportCode, String sourceAirportId,
                 String destinationAirportCode, String destinationAirportId, String codeshare, Integer stops,
                 String equipment, Double price) {
        this.airlineCode = airlineCode;
        this.airlineId = airlineId;
        this.sourceAirportCode = sourceAirportCode;
        this.sourceAirportId = sourceAirportId;
        this.destinationAirportCode = destinationAirportCode;
        this.destinationAirportId = destinationAirportId;
        this.codeshare = codeshare;
        this.stops = stops;
        this.equipment = equipment;
        this.price = price;
    }

}
