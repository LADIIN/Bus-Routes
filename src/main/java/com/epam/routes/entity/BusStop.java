package com.epam.routes.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusStop {
    private static final Logger LOGGER = Logger.getLogger(BusStop.class.getName());
    private static final int STOP_TIME = 1;

    private String name;
    private int busesCapacity;
    private int passengersOnStop;
    private final Semaphore busStopSemaphore;
    private final Lock busStopLock = new ReentrantLock();

    @JsonCreator
    public BusStop(@JsonProperty("busesCapacity") int busesCapacity) {
        this.busStopSemaphore = new Semaphore(busesCapacity, true);
    }

    public BusStop(String name, int busesCapacity) {
        this.name = name;
        this.busesCapacity = busesCapacity;
        this.busStopSemaphore = new Semaphore(busesCapacity, true);
    }

    public void exchangePassengers(Bus bus) {
        try {
            busStopSemaphore.acquire();
            busStopLock.lock();

            LOGGER.log(Level.INFO, String.format("%s arrived at %s", bus, this));

            leavePassengers(bus);
            enterPassengers(bus);

            TimeUnit.SECONDS.sleep(STOP_TIME);
            LOGGER.log(Level.INFO, String.format("%s left %s", bus, this));

        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Can't exchange passengers on bus stop cause:" + e);
        } finally {
            busStopSemaphore.release();
            busStopLock.unlock();
        }
    }

    private void leavePassengers(Bus bus) {
        int passengerLeavingBus = (int) (Math.random() * bus.getPassengerSits());
        passengersOnStop += passengerLeavingBus;
        bus.removePassengers(passengerLeavingBus);
    }

    private void enterPassengers(Bus bus) {
        int passengerEnteringBus = (int) Math.round(Math.random() * passengersOnStop);
        int freeSits = bus.getFreeSits();

        if (passengerEnteringBus > freeSits) {
            passengersOnStop += passengerEnteringBus - freeSits;
            passengerEnteringBus = freeSits;
        }
        bus.addPassengers(passengerEnteringBus);
        passengersOnStop -= passengerEnteringBus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBusesCapacity(int busesCapacity) {
        this.busesCapacity = busesCapacity;
    }

    public void setPassengersOnStop(int passengersOnStop) {
        this.passengersOnStop = passengersOnStop;
    }

    @Override
    public String toString() {
        return String.format("Bus stop: {name = '%s', passengers on stop: %d}", name, passengersOnStop);
    }

    public Semaphore getBusStopSemaphore() {
        return busStopSemaphore;
    }

    public String getName() {
        return name;
    }

    public int getBusesCapacity() {
        return busesCapacity;
    }
}
