package com.example.flightadvisor.repository;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCity (City city);

    @Query(value = "SELECT c FROM Comment c WHERE c.city=?1 ORDER BY c.dateCreated DESC")
    Page<Comment> findAllCommentsWithPagination(City c, Pageable pageable);
}
