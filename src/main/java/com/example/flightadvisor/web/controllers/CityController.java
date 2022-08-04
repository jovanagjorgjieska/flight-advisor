package com.example.flightadvisor.web.controllers;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Comment;
import com.example.flightadvisor.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City>getAllCities(){
        return this.cityService.findAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<City> findByName(@PathVariable String name){
        return this.cityService.findByName(name)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/addCity")
    public ResponseEntity<City> save(@RequestBody City city){
        return this.cityService.save(city.getName(), city.getCountry(), city.getDescription())
                .map(city1 -> ResponseEntity.ok().body(city1))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/addComment/{id}")
    public ResponseEntity<City> addComment(@PathVariable Long id, @RequestBody Comment comment){
        return this.cityService.addCommentForCity(id, comment)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/editComment/{cityId}/{commentId}")
    public ResponseEntity<City> editComment(@PathVariable Long cityId, @PathVariable Long commentId, @RequestBody Comment comment){
        return this.cityService.editCommentOfCity(cityId, commentId, comment)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/deleteComment/{cityId}/{commentId}")
    public ResponseEntity<City> deleteComment(@PathVariable Long cityId, @PathVariable Long commentId){
        return this.cityService.deleteCommentOfCity(cityId, commentId)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
