package com.example.flightadvisor.service;

import com.example.flightadvisor.model.Airport;
import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Route;

import java.util.List;
import java.util.Optional;

public interface AirportService {
        Optional<Airport> findById(Long id);

        Optional<Airport> findByIataCode(String iataCode);

        Optional<Airport> findByIcaoCode(String icaoCode);
}
