package com.epam.routes;

import java.util.List;

public class Route {
    private final String name;
    private final List<BusStop> busStops;

    public Route(String name, List<BusStop> busStops) {
        this.name = name;
        this.busStops = busStops;
    }

    public String getName() {
        return name;
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }
}
