package com.example.flightadvisor.web.controllers;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Comment;
import com.example.flightadvisor.service.CityService;
import com.example.flightadvisor.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CityService cityService;
    private final CommentService commentService;

    public CommentsController(CityService cityService, CommentService commentService) {
        this.cityService = cityService;
        this.commentService = commentService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{cityName}")
    public ResponseEntity<City> addComment(@PathVariable String cityName, @RequestBody Comment comment, Principal principal){
        String username = principal.getName();

        return this.cityService.addCommentForCity(cityName, comment, username)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{cityName}/{commentId}")
    public ResponseEntity<City> editComment(@PathVariable String cityName,
                                            @PathVariable Long commentId,
                                            @RequestBody Comment comment,
                                            Principal principal){
        String username = principal.getName();

        return this.cityService.editCommentOfCity(cityName, commentId, comment, username)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{cityName}/{commentId}")
    public ResponseEntity<City> deleteComment(@PathVariable String cityName, @PathVariable Long commentId, Principal principal){
        String username = principal.getName();

        return this.cityService.deleteCommentOfCity(cityName, commentId, username)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

