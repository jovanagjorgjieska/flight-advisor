package com.example.flightadvisor.service;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    Optional<Route> findById(String id);

    List<Route> findCheapestFlight(City cityA, City cityB);
}
