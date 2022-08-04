package com.example.flightadvisor.service.impl;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Route;
import com.example.flightadvisor.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
    @Override
    public List<Route> findAll() {
        return null;
    }

    @Override
    public Optional<Route> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Route> save(Long airlineId, String airlineCode, String sourceAirportCode, String sourceAirportId, String destinationAirportCode, String destinationAirportId, Double price, Integer stops) {
        return Optional.empty();
    }

    @Override
    public List<Route> findCheapestFlight(City cityA, City cityB) {
        return null;
    }
}
