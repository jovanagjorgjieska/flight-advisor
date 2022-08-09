package com.example.flightadvisor.config;

import com.example.flightadvisor.model.Airport;
import com.example.flightadvisor.service.CityService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class AirportProcessor implements ItemProcessor<Airport,Airport> {

    @Autowired
    private CityService cityService;
    @Override
    public Airport process(Airport airport) throws Exception {
        if(this.cityService.findByName(airport.getCity()).isPresent())
             return airport;
        else
            return null;
//        return airport;
    }
}
