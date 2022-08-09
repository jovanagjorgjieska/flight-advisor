package com.example.flightadvisor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String description;

    private LocalDate dateCreated;

    private LocalDate dateModified;

    private String creator;

    public Comment(String description) {
        this.description = description;
        this.dateCreated = LocalDate.now();
    }
}
