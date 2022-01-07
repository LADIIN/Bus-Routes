package com.epam.routes.entity;

public class Bus implements Runnable {
    private long id;
    private int passengerSits;
    private int passengers;

    public Bus() {
    }

    @Override
    public void run() {
        Route route = Route.getInstance();

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
