package com.example.flightadvisor.web.controllers;

import com.example.flightadvisor.exceptions.CityNotFoundException;
import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Comment;
import com.example.flightadvisor.service.CityService;
import com.example.flightadvisor.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    @PostMapping("/{cityId}")
    public ResponseEntity<City> addComment(@PathVariable Long cityId, @RequestBody Comment comment, Principal principal){
        String username = principal.getName();

        return this.cityService.addCommentForCity(cityId, comment, username)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{cityId}/{commentId}")
    public ResponseEntity<City> editComment(@PathVariable Long cityId,
                                            @PathVariable Long commentId,
                                            @RequestBody Comment comment,
                                            Principal principal){
        String username = principal.getName();

        return this.cityService.editCommentOfCity(cityId, commentId, comment, username)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{cityId}/{commentId}")
    public ResponseEntity<City> deleteComment(@PathVariable Long cityId, @PathVariable Long commentId, Principal principal){
        String username = principal.getName();

        return this.cityService.deleteCommentOfCity(cityId, commentId, username)
                .map(city -> ResponseEntity.ok().body(city))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

