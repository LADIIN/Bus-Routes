package com.epam.routes.entity;

import com.epam.routes.util.IdGenerator;

public class Bus implements Runnable {
    private final long id;
    private Route route;
    private int passengerSits;
    private int passengers;

    public Bus() {
        this.id = IdGenerator.generatorId();
    }

    public Bus(Route route, int passengersCapacity) {
        this.id = IdGenerator.generatorId();
        this.route = route;
        this.passengerSits = passengersCapacity;
        this.passengers = passengersCapacity;
    }

    @Override
    public void run() {
        for (BusStop busStop : route.getBusStops()) {
            busStop.exchangePassengers(this);
        }
    }

    @Override
    public String toString() {
        return String.format("Bus: {id = %d, passengerSits = %d, passengers = %d}", id, passengerSits, passengers);
    }

    public long getId() {
        return id;
    }

    public Route getRoute() {
        return route;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setPassengerSits(int passengerSits) {
        this.passengerSits = passengerSits;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
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
