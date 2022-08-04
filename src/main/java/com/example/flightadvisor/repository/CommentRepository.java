package com.example.flightadvisor.repository;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
