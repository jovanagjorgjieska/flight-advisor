package com.example.flightadvisor.web.controllers;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Route;
import com.example.flightadvisor.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/routes")
@AllArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{cityA}/{cityB}")
    public Route findCheapestFlight(@PathVariable String cityA, @PathVariable String cityB){
         return this.routeService.findCheapestFlight(cityA, cityB);
    }

    //    @GetMapping("/{cityA}/{cityB}")
    //    public ResponseEntity<List<Route>> findCheapestFlight(@PathVariable String cityA, @PathVariable String cityB){
    //        return ResponseEntity.ok().body(routeService.findCheapestFlight());
    //    }
}
