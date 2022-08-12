package com.example.flightadvisor.service.impl;

import com.example.flightadvisor.model.Airport;
import com.example.flightadvisor.repository.AirportRepository;
import com.example.flightadvisor.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public Optional<Airport> findById(Long id) {
        return this.airportRepository.findById(id);
    }

    @Override
    public Optional<Airport> findByIataCode(String iataCode) {
        return this.airportRepository.findByIataCode(iataCode);
    }

    @Override
    public Optional<Airport> findByIcaoCode(String icaoCode) {
        return this.airportRepository.findByIcaoCode(icaoCode);
    }

    @Override
    public Optional<Airport> findByCode(String code) {
        if(code.length() == 3){
            return this.airportRepository.findByIataCode(code);
        }
        else{
            return this.airportRepository.findByIcaoCode(code);
        }
    }
}
