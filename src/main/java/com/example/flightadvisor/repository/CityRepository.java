package com.example.flightadvisor.repository;

import com.example.flightadvisor.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City>findByNameLike(String name);
    Optional<City>findByName(String name);
}
