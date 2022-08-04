package com.example.flightadvisor.service.impl;

import com.example.flightadvisor.model.Airport;
import com.example.flightadvisor.model.City;
import com.example.flightadvisor.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {
    @Override
    public List<Airport> findAll() {
        return null;
    }

    @Override
    public Optional<Airport> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Airport> saveAirport(Long airportId, String name, City city, String country, String iataCode, String icaoCode) {
        return Optional.empty();
    }
}
