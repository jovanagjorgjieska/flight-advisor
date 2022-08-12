package com.example.flightadvisor.model.dijkstra;

import lombok.Data;

@Data
public class Vertex {
    private String name;
    private boolean permanent;
    private String predecessor;
    private Double pathLength;

    public Vertex(String name)
    {
        this.name = name;
    }
    public String toString()
    {
        return name;
    }
}
