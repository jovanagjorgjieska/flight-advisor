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

//    private Double latitude;
//
//    private Double longitude;
//
//    private Double altitude;
//
//    private Double timezone;
//
//    private String dst;
//
//    private String tz;
//
//    private String type;
//
//    private String source;
//
//    public Airport(Long airportId, String name, String city, String country, String iataCode, String icaoCode,
//                   Double latitude, Double longitude, Double altitude, Double timezone, String dst, String tz,
//                   String type, String source) {
//        this.airportId = airportId;
//        this.name = name;
//        this.city = city;
//        this.country = country;
//        this.iataCode = iataCode;
//        this.icaoCode = icaoCode;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.altitude = altitude;
//        this.timezone = timezone;
//        this.dst = dst;
//        this.tz = tz;
//        this.type = type;
//        this.source = source;
//    }


    public Airport(Long airportId, String name, String city, String country, String iataCode, String icaoCode) {
        this.airportId = airportId;
        this.name = name;
        this.city = city;
        this.country = country;
        this.iataCode = iataCode;
        this.icaoCode = icaoCode;
    }
}
