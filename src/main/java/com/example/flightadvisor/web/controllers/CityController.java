package com.example.flightadvisor.web.controllers;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.User;
import com.example.flightadvisor.service.CityService;
import com.example.flightadvisor.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;
    private final UserService userService;

    public CityController(CityService cityService, UserService userService) {
        this.cityService = cityService;
        this.userService = userService;
    }

//    @PreAuthorize("hasRole('USER')")
//    @GetMapping
//    public List<City>getAllCities(){
//        return this.cityService.findAll();
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/{name}")
//    public ResponseEntity<City> findByName(@PathVariable String name, Principal principal){
//        return this.cityService.findByName(name)
//                .map(city -> ResponseEntity.ok().body(city))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<City>getAllCities(Pageable pageable){
        if(pageable.getPageSize() != 0){
            return this.cityService.findAllWithPagination(pageable);
        }else return this.cityService.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{name}")
    public ResponseEntity<City> findByName(@PathVariable String name, Principal principal, Pageable pageable){
        if(pageable.getPageSize() != 0){
            return this.cityService.findByNameWithPagination(name, pageable)
                    .map(city -> ResponseEntity.ok().body(city))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }else {
            return this.cityService.findByName(name)
                    .map(city -> ResponseEntity.ok().body(city))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<City> save(@RequestBody City city){
        return this.cityService.save(city.getName(), city.getCountry(), city.getDescription())
                .map(city1 -> ResponseEntity.ok().body(city1))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
