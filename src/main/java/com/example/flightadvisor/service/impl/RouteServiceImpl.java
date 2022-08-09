package com.example.flightadvisor.service.impl;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Route;
import com.example.flightadvisor.repository.RouteRepository;
import com.example.flightadvisor.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Optional<Route> findById(String id) {
        return this.routeRepository.findById(id);
    }

    @Override
    public List<Route> findCheapestFlight(City cityA, City cityB) {
        return null;
    }
}
