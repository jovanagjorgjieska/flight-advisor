package com.example.flightadvisor.service;

import com.example.flightadvisor.model.Comment;

import java.util.Optional;

public interface CommentService {
    Optional<Comment> editComment(Long commentId, String description);

    void deleteComment(Long commentId);

}
