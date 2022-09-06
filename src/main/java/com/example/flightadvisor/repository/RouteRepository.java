package com.example.flightadvisor.repository;

import com.example.flightadvisor.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, String> {
    Optional<Route> findBySourceAirportCodeAndDestinationAirportCode(String source, String destination);
}
