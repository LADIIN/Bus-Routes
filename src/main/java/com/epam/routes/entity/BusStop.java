package com.epam.routes.entity;

import com.epam.routes.util.IdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BusStop {
    private static final Logger LOGGER = LogManager.getLogger(BusStop.class);
    private static final int BUSES_CAPACITY = 2;

    private final long id;
    private int passengersOnStop;
    private final Semaphore busStopSemaphore;
    private final Lock busStopLock = new ReentrantLock();

    public BusStop() {
        id = IdGenerator.generatorId();
        this.busStopSemaphore = new Semaphore(BUSES_CAPACITY, true);
    }

    public void exchangePassengers(Bus bus) {
        try {
            busStopSemaphore.acquire();
            busStopLock.lock();
            LOGGER.info(String.format("%s arrived at %s", bus, this));

            leavePassengersFrom(bus);
            enterPassengersTo(bus);

            LOGGER.info(String.format("%s left %s", bus, this));
        } catch (InterruptedException e) {
            throw new RuntimeException("Can't exchange passengers cause:", e);
        } finally {
            busStopSemaphore.release();
            busStopLock.unlock();
        }
    }

    private void leavePassengersFrom(Bus bus) {
        int passengerLeavingBus = (int) (Math.random() * bus.getPassengers());
        passengersOnStop += passengerLeavingBus;
        bus.removePassengers(passengerLeavingBus);
    }

    private void enterPassengersTo(Bus bus) {
        int passengerEnteringBus = (int) Math.round(Math.random() * passengersOnStop);
        int freeSits = bus.getFreeSits();

        if (passengerEnteringBus > freeSits) {
            passengerEnteringBus = freeSits;
        }
        bus.addPassengers(passengerEnteringBus);
        passengersOnStop -= passengerEnteringBus;
    }

    @Override
    public String toString() {
        return String.format("Bus stop: {id = %d, passengers on stop: %d}", id, passengersOnStop);
    }

    public Semaphore getBusStopSemaphore() {
        return busStopSemaphore;
    }

    public int getPassengersOnStop() {
        return passengersOnStop;
    }
}
