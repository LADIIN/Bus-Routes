package com.epam.routes.entity;

import java.util.List;

public class Route {
    private String name;
    private List<BusStop> busStops;
    private static volatile Route instance;

    private Route() {

    }

    public static Route getInstance() {
        Route localInstance = instance;
        if (localInstance == null) {
            synchronized (Route.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Route();
                }
            }
        }
        return localInstance;
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
