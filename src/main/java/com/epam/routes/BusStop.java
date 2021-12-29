package com.epam.routes;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusStop {
    private static final Logger LOGGER = Logger.getLogger(BusStop.class.getName());

    private final String name;
    private final int busesCapacity;
    private final List<Bus> stoppedBuses = new ArrayList<>();
    private int passengersOnStop;

    private final Semaphore busStopSemaphore;
    private final Lock busStopLock = new ReentrantLock();

    public BusStop(String name, int busesCapacity) {
        this.name = name;
        this.busesCapacity = busesCapacity;
        this.busStopSemaphore = new Semaphore(busesCapacity, true);
    }

    public void exchangePassengers(Bus bus) {
        try {
            busStopSemaphore.acquire();
            busStopLock.lock();
            stoppedBuses.add(bus);

            LOGGER.log(Level.INFO, String.format("Bus %s arrived on stop '%s' Passengers in bus: %d Passengers on stop: %d",
                    bus.getId(), name, bus.getPassengers(), passengersOnStop));
            LOGGER.log(Level.INFO, String.format("Bus %s:Buses on stop '%s' - %d", bus.getId(), name, stoppedBuses.size()));

            //leaving from current bus
            int passengerLeavingBus = (int) (Math.random() * bus.getPassengerSits());
            passengersOnStop += passengerLeavingBus;

            bus.removePassengers(passengerLeavingBus);

            LOGGER.log(Level.INFO, String.format("Bus %s: Passengers left: %d, Passengers in bus: %d Passengers on stop %s: %d",
                    bus.getId(), passengerLeavingBus, bus.getPassengers(), name, passengersOnStop));


            //entering from stop to all buses
            for (Bus stoppedBus : stoppedBuses) {
                enterPassengers(stoppedBus);
            }

            LOGGER.log(Level.INFO, String.format("Bus %s: Passengers on stop %s: %d", bus.getId(), name, passengersOnStop));
        } catch (InterruptedException e) {
            //TODO: add logger
        } finally {
            busStopSemaphore.release();
            busStopLock.unlock();
            stoppedBuses.remove(bus);
        }
    }

    private void enterPassengers(Bus bus) {
        int passengerEnteringBus = (int) Math.round(Math.random() * passengersOnStop);

        int freeSits = bus.getFreeSits();

//        LOGGER.log(Level.INFO, String.format("Bus %d: Passenger going to enter: %d Free sits in bus: %d", bus.getId(),
//                passengerEnteringBus, freeSits));

        if (passengerEnteringBus > freeSits) {
            passengersOnStop += passengerEnteringBus - freeSits;
            passengerEnteringBus = freeSits;
        }

        bus.addPassengers(passengerEnteringBus);
        passengersOnStop -= passengerEnteringBus;
    }

    public String getName() {
        return name;
    }

    public int getBusesCapacity() {
        return busesCapacity;
    }

    public List<Bus> getStoppedBuses() {
        return stoppedBuses;
    }
}
