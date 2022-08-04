package com.example.flightadvisor.service;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CityService {

    List<City> findAll();

    Optional<City> findById(Long cityId);

    Optional<City> findByName(String name);

    Optional<City> save(String name, String country, String description);

    Optional<City> addCommentForCity(Long cityId, Comment comment);

    Optional<City> editCommentOfCity(Long cityId, Long commentId, Comment comment);

    Optional<City> deleteCommentOfCity(Long cityId, Long commentId);
}
