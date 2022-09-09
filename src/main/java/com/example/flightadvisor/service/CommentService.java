package com.example.flightadvisor.service;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CommentService {
    Optional<Comment> editComment(Long commentId, String description, City city);

    void deleteComment(Long commentId);

}
