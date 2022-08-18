package com.example.flightadvisor.web.controllers;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Comment;
import com.example.flightadvisor.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CityService cityService;

    public CommentsController(CityService cityService) {
        this.cityService = cityService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{cityId}")
    public ResponseEntity<City> addComment(@PathVariable Long cityId, @RequestBody Comment comment){
        return this.cityService.addCommentForCity(cityId, comment)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{cityId}/{commentId}")
    public ResponseEntity<City> editComment(@PathVariable Long cityId, @PathVariable Long commentId, @RequestBody Comment comment){
        return this.cityService.editCommentOfCity(cityId, commentId, comment)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{cityId}/{commentId}")
    public ResponseEntity<City> deleteComment(@PathVariable Long cityId, @PathVariable Long commentId){
        return this.cityService.deleteCommentOfCity(cityId, commentId)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

