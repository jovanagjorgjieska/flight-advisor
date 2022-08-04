package com.example.flightadvisor.config;

import com.example.flightadvisor.model.Airport;
import org.springframework.batch.item.ItemProcessor;

public class AirportProcessor implements ItemProcessor<Airport,Airport> {
    @Override
    public Airport process(Airport airport) throws Exception {
        return airport;
    }
}
