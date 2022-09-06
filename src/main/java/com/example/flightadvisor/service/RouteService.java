package com.example.flightadvisor.service;

import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    Optional<Route> findById(String id);
    Route findCheapestFlight(String cityA, String cityB);
    void dijkstra(int city);
    String tempVertexMinPL();
    boolean isAdjacent(int u, int v);
    int getIndex(String s);
    void insertVertex(String name);
    public void insertEdge(String s1, String s2, double price);
}
