package com.example.flightadvisor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    private String name;

    private String country;

    private String description;

    @OneToMany
    private List<Comment> comments;

    public City(String name, String country, String description) {
        this.name = name;
        this.country = country;
        this.description = description;
        this.comments = new ArrayList<>();
    }
}
