package com.example.flightadvisor.service;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    List<Route> findAll();

    Optional<Route> findById(Long id);

    Optional<Route> save (Long airlineId, String airlineCode, String sourceAirportCode,
                          String sourceAirportId, String destinationAirportCode, String destinationAirportId,
                          Double price, Integer stops);


    List<Route> findCheapestFlight(City cityA, City cityB);
}
