package com.example.flightadvisor.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String description;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;

    @ManyToOne
    private User creator;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cityId", nullable = false)
    private City city;

    public Comment(String description, User creator, City city) {
        this.description = description;
        this.creator = creator;
        this.city = city;
        this.dateCreated = LocalDateTime.now();
    }
}
