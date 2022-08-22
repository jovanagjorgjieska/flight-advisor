package com.example.flightadvisor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@Entity
public class Airport {

    @Id
    private Long airportId;

    private String name;

    private String city;

    private String country;

    private String iataCode;

    private String icaoCode;


    public Airport(Long airportId, String name, String city, String country, String iataCode, String icaoCode) {
        this.airportId = airportId;
        this.name = name;
        this.city = city;
        this.country = country;
        this.iataCode = iataCode;
        this.icaoCode = icaoCode;
    }
}
