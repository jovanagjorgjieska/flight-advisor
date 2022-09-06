package com.example.flightadvisor.service;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Comment;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CityService {

    List<City> findAll();

    List<City> findAllWithPagination(Pageable pageable);

    Optional<City> findByName(String name);

    Optional<City> findByNameWithPagination(String name, Pageable pageable);

    Optional<City> save(String name, String country, String description);

    Optional<City> addCommentForCity(Long cityId, Comment comment);

    Optional<City> editCommentOfCity(Long cityId, Long commentId, Comment comment, String modifier);

    Optional<City> deleteCommentOfCity(Long cityId, Long commentId, String username);
}
