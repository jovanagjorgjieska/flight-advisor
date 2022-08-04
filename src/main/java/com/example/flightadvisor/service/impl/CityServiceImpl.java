package com.example.flightadvisor.service.impl;

import com.example.flightadvisor.exceptions.CityNotFoundException;
import com.example.flightadvisor.exceptions.CommentNotFoundException;
import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Comment;
import com.example.flightadvisor.repository.CityRepository;
import com.example.flightadvisor.repository.CommentRepository;
import com.example.flightadvisor.service.CityService;
import com.example.flightadvisor.service.CommentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    public CityServiceImpl(CityRepository cityRepository, CommentRepository commentRepository, CommentService commentService) {
        this.cityRepository = cityRepository;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
    }

    @Override
    public List<City> findAll() {
        return this.cityRepository.findAll();
    }

    @Override
    public Optional<City> findById(Long cityId) {
        return this.cityRepository.findById(cityId);
    }

    @Override
    public Optional<City> findByName(String name) {
        return this.cityRepository.findByNameLike(name);
    }

    @Override
    public Optional<City> save(String name, String country, String description) {
        City city = new City(name, country, description);
        this.cityRepository.save(city);
        return Optional.of(city);
    }

    @Override
    @Transactional
    public Optional<City> addCommentForCity(Long cityId, Comment comment) {
        City city = this.cityRepository.findById(cityId).orElseThrow(() -> new CityNotFoundException(cityId));

        Comment commentToAdd = new Comment(comment.getDescription());
        this.commentRepository.save(commentToAdd);

        city.getComments().add(commentToAdd);
        return Optional.of(this.cityRepository.save(city));
    }

    @Override
    public Optional<City> editCommentOfCity(Long cityId, Long commentId, Comment comment) {
        City city = this.cityRepository.findById(cityId).orElseThrow(() -> new CityNotFoundException(cityId));

        this.commentService.editComment(commentId, comment.getDescription());
        return Optional.of(city);
    }

    @Override
    public Optional<City> deleteCommentOfCity(Long cityId, Long commentId) {
        City city = this.cityRepository.findById(cityId).orElseThrow(() -> new CityNotFoundException(cityId));
        Comment commentToDelete = this.commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(commentId));
        city.getComments().remove(commentToDelete);

        this.commentRepository.delete(commentToDelete);
        return Optional.of(city);
    }


}
