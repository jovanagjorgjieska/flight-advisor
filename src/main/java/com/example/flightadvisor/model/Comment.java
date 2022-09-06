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

    private Long cityId;

    public Comment(String description, String creator, Long cityId) {
        this.description = description;
        this.creator = creator;
        this.cityId = cityId;
        this.dateCreated = LocalDate.now();
    }
}
