package com.example.flightadvisor.web.controllers;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Comment;
import com.example.flightadvisor.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<City>getAllCities(){
        return this.cityService.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{name}")
    public ResponseEntity<City> findByName(@PathVariable String name){
        return this.cityService.findByName(name)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<City> save(@RequestBody City city){
        return this.cityService.save(city.getName(), city.getCountry(), city.getDescription())
                .map(city1 -> ResponseEntity.ok().body(city1))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
