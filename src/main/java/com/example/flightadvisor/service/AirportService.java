package com.example.flightadvisor.service;

import com.example.flightadvisor.model.Airport;
import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Route;

import java.util.List;
import java.util.Optional;

public interface AirportService {

    List<Airport>findAll();

    Optional<Airport> findById(Long id);

    Optional<Airport> saveAirport(Long airportId, String name, City city, String country, String iataCode, String icaoCode);
}
