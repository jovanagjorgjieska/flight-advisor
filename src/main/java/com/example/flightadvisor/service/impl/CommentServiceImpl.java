package com.example.flightadvisor.service.impl;

import com.example.flightadvisor.exceptions.CommentNotFoundException;
import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Comment;
import com.example.flightadvisor.repository.CityRepository;
import com.example.flightadvisor.repository.CommentRepository;
import com.example.flightadvisor.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CityRepository cityRepository;

    public CommentServiceImpl(CommentRepository commentRepository, CityRepository cityRepository) {
        this.commentRepository = commentRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public Optional<Comment> editComment(Long commentId, String description, City city) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException(commentId));
        comment.setDescription(description);
        comment.setDateModified(LocalDateTime.now());
        comment.setCity(city);
        this.commentRepository.save(comment);
        return Optional.of(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        this.commentRepository.deleteById(commentId);
    }
}
