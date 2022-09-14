package com.example.flightadvisor.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

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

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
    private List<Comment> comments;

    public City(String name, String country, String description) {
        this.name = name;
        this.country = country;
        this.description = description;
        this.comments = new ArrayList<>();
    }

    public City(String name, String country) {
        this.name = name;
        this.country = country;
        this.description = null;
        this.comments = new ArrayList<>();
    }

}
