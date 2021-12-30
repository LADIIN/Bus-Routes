package com.epam.routes.entity;

import java.util.List;

public class Route {
    private  String name;
    private  List<BusStop> busStops;

    public Route(){

    }

    public Route(String name, List<BusStop> busStops) {
        this.name = name;
        this.busStops = busStops;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBusStops(List<BusStop> busStops) {
        this.busStops = busStops;
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }
}
