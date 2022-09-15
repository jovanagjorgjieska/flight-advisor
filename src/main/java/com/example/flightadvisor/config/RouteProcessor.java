package com.example.flightadvisor.config;

import com.example.flightadvisor.model.Route;
import com.example.flightadvisor.service.AirportService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class RouteProcessor implements ItemProcessor<Route,Route> {

    @Autowired
    private AirportService airportService;
    @Override
    public Route process(Route route) throws Exception {
//        if((this.airportService.findByIataCode(route.getSourceAirportCode()).isPresent() ||
//                this.airportService.findByIcaoCode(route.getSourceAirportCode()).isPresent()) &&
//                (this.airportService.findByIataCode(route.getDestinationAirportCode()).isPresent() ||
//                        this.airportService.findByIcaoCode(route.getDestinationAirportCode()).isPresent())){
//            return route;
//        }
//        else return null;
        return route;
    }
}
