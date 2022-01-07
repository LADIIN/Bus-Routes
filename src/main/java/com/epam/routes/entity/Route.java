package com.epam.routes.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Route {
    private static final int BUS_STOPS_AMOUNT = 4;

    private static Route instance;
    private static final Lock lock = new ReentrantLock();
    private final List<BusStop> busStops = new ArrayList<>();

    private Route() {
    }

    public static Route getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new Route();
                instance.initializeBusStops();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    private void initializeBusStops() {
        IntStream.range(0, BUS_STOPS_AMOUNT).mapToObj(busStop -> new BusStop()).forEach(busStops::add);
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }
}
