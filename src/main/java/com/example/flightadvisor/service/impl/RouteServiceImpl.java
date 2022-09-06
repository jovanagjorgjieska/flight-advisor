package com.example.flightadvisor.service.impl;

import com.example.flightadvisor.exceptions.AirportNotFoundException;
import com.example.flightadvisor.exceptions.RouteNotFoundException;
import com.example.flightadvisor.model.Airport;
import com.example.flightadvisor.model.City;
import com.example.flightadvisor.model.Route;
import com.example.flightadvisor.model.dijkstra.Vertex;
import com.example.flightadvisor.repository.CityRepository;
import com.example.flightadvisor.repository.RouteRepository;
import com.example.flightadvisor.service.AirportService;
import com.example.flightadvisor.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final CityRepository cityRepository;
    private final AirportService airportService;
    int n = 0;


    public RouteServiceImpl(RouteRepository routeRepository, CityRepository cityRepository, AirportService airportService) {
        this.routeRepository = routeRepository;
        this.cityRepository = cityRepository;
        this.airportService = airportService;
    }

    private static final double INFINITY = 99999.9;

    Vertex[] vertexList = new Vertex[1000];
    double [][] neighbours = new double[1000][1000];

    @Override
    public Optional<Route> findById(String id) {
        return this.routeRepository.findById(id);
    }

    @Override
    public Route findCheapestFlight(String source, String destination) {
        List<City>cities = this.cityRepository.findAll();
        for(City c:cities){
            this.insertVertex(c.getName());
        }

        List<Route> routes = this.routeRepository.findAll();
        for (Route r:routes){
            Airport airport1 = this.airportService.findByCode(r.getSourceAirportCode()).orElseThrow(() -> new AirportNotFoundException(r.getSourceAirportCode()));
            Airport airport2 = this.airportService.findByCode(r.getDestinationAirportCode()).orElseThrow(() -> new AirportNotFoundException(r.getDestinationAirportCode()));

            this.insertEdge(airport1.getCity(), airport2.getCity(), r.getPrice());
        }

        int s = getIndex(source);
        int d = getIndex(destination);

        dijkstra(s);

        if(vertexList[d].getPathLength() == INFINITY){
            //System.out.println("There is no route from " + source + " to " + destination + "\n");
            //ovde namesto ovaj print ke frli isklucok
            throw new RouteNotFoundException();
        } else{
            String pred;
            String [] paths = new String[1000];
            Stack<Route> path = new Stack<>();
            int totalPrice=0;
            int count=0;

            while(d != s)
            {
                count++;
//                path[count] = destination;
                pred = vertexList[d].getPredecessor();
                Airport sourceAirport = this.airportService.findByCityName(pred).orElseThrow(AirportNotFoundException::new);
                Airport destinationAirport = this.airportService.findByCityName(destination).orElseThrow(AirportNotFoundException::new);

                String sourceCode = null;
                String destinationCode = null;
                if(sourceAirport.getIataCode() != null)
                    sourceCode = sourceAirport.getIataCode();
                else sourceCode = sourceAirport.getIcaoCode();

                if(destinationAirport.getIataCode() != null)
                    destinationCode = destinationAirport.getIataCode();
                else destinationCode = destinationAirport.getIcaoCode();

                Route route = this.routeRepository.findBySourceAirportCodeAndDestinationAirportCode(sourceCode, destinationCode)
                                .orElseThrow(RouteNotFoundException::new);
                path.push(route);
                pred = vertexList[d].getPredecessor();
                int p_index = getIndex(pred);
                totalPrice += neighbours[p_index][d];
                d = p_index;
            }
//            count++;
//            path[count]=source;

//            System.out.print("Shortest Path is : ");
            for(int i=count; i>=1; i--)
//                System.out.print(path[i] + " ");
//            System.out.println("\n Total price is : " + totalPrice + "\n");
                return path.pop();
        }
        return null;
    }

    @Override
    public void dijkstra(int city) {
        String current;

        int n = this.cityRepository.findAll().size();
        for(int i=0; i<n; i++)
        {
            vertexList[i].setPermanent(false);
            vertexList[i].setPathLength(INFINITY);
            vertexList[i].setPredecessor(null);
        }

        vertexList[city].setPathLength(0.0);

        while(true)
        {
            current = tempVertexMinPL();

            if(current==null)
                return;

            int currentIndex = getIndex(current);
            vertexList[currentIndex].setPermanent(true);

            for(int i=0; i<n; i++)
            {
                if(isAdjacent(currentIndex,i) && !vertexList[i].isPermanent())
                    if( vertexList[currentIndex].getPathLength() + neighbours[currentIndex][i] < vertexList[i].getPathLength())
                    {
                        vertexList[i].setPredecessor(current);
                        vertexList[i].setPathLength(vertexList[currentIndex].getPathLength() + neighbours[currentIndex][i]);
                    }
            }
        }
    }

    @Override
    public String tempVertexMinPL() {
        double min = INFINITY;
        int n = this.cityRepository.findAll().size();
        String x = null;
        for(int i = 0; i < n; i++)
        {
            if(!vertexList[i].isPermanent() && vertexList[i].getPathLength() < min)
            {
                min = vertexList[i].getPathLength();
                x = vertexList[i].getName();
            }
        }
        return x;
    }

    @Override
    public boolean isAdjacent(int u, int v) {
        return (neighbours[u][v]!=0);
    }

    @Override
    public int getIndex(String s)
    {
        int n = this.cityRepository.findAll().size();
        for(int i=0; i<n; i++)
            if(s.equals(vertexList[i].getName()))
                return i;
        throw new RuntimeException("Invalid Vertex");
    }

    @Override
    public void insertVertex(String name) {
        vertexList[n++] = new Vertex(name);
    }

    @Override
    public void insertEdge(String s1, String s2, double price) {
        int u = getIndex(s1);
        int v = getIndex(s2);
        if(u==v)
            throw new IllegalArgumentException("Not a valid edge");

        if(neighbours[u][v] !=0 )
            System.out.print("Edge already present");
        else
        {
            neighbours[u][v]=price;
        }
    }
}
