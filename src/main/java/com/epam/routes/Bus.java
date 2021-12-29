package com.epam.routes;

public class Bus implements Runnable {
    private final Integer id;
    private final Route route;
    private final int passengerSits;
    private int passengers;

    public Bus(Integer id, Route route, int passengersCapacity) {
        this.id = id;
        this.route = route;
        this.passengerSits = passengersCapacity;
        //TODO: rebuild
        this.passengers = passengersCapacity;
    }

    @Override
    public void run() {
        for (BusStop busStop : route.getBusStops()) {
            busStop.exchangePassengers(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public Route getRoute() {
        return route;
    }

    public int getPassengers() {
        return passengers;
    }

    public int getFreeSits() {
        return passengerSits - passengers;
    }

    public void addPassengers(int passengers) {
        this.passengers += passengers;
    }

    public void removePassengers(int passengers) {
        this.passengers -= passengers;
    }

    public int getPassengerSits() {
        return passengerSits;
    }
}
