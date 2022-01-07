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
        Route localInstance = instance;
        if (localInstance == null) {
            lock.lock();
            try {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Route();
                    instance.initializeBusStops();
                }
            } finally {
                lock.unlock();
            }
        }
        return localInstance;
    }

    private void initializeBusStops() {
        IntStream.range(0, BUS_STOPS_AMOUNT).mapToObj(busStop -> new BusStop()).forEach(busStops::add);
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }
}
