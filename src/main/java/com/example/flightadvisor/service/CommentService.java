package com.example.flightadvisor.service;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

//    List<Comment> findAllCommentsForCity(Long cityId);

//    Optional<Comment> saveComment(String description);

    Optional<Comment> editComment(Long commentId, String description);

    void deleteComment(Long commentId);

}
