package com.example.flightadvisor.config;

import com.example.flightadvisor.model.Airport;
import com.example.flightadvisor.model.City;
import com.example.flightadvisor.repository.CityRepository;
import com.example.flightadvisor.service.CityService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

public class AirportProcessor implements ItemProcessor<Airport,Airport> {

    @Autowired
    private CityRepository cityRepository;
    @Override
    public Airport process(Airport airport) throws Exception {
//        if(this.cityService.findByName(airport.getCity()).isPresent())
//             return airport;
//        else
//            return null;
        City city = new City(airport.getCity(), airport.getCountry());
        this.cityRepository.save(city);
        return airport;
    }
}
