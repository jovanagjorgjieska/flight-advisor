package com.example.flightadvisor.config;

import com.example.flightadvisor.model.Route;
import org.springframework.batch.item.ItemProcessor;

public class RouteProcessor implements ItemProcessor<Route,Route> {
    @Override
    public Route process(Route route) throws Exception {
        return route;
    }
}
