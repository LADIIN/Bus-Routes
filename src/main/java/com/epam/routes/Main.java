package com.epam.routes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        BusStop firstBusStop = new BusStop("001", 3);
        BusStop secondBusStop = new BusStop("002", 3);
        BusStop thirdBusStop = new BusStop("003", 3);

        List<BusStop> busStops = Arrays.asList(firstBusStop, secondBusStop, thirdBusStop);

        Route route = new Route("Route", busStops);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Thread firstBus = new Thread(new Bus(1, route, 20));
        executorService.submit(firstBus);
        Thread secondBus = new Thread(new Bus(2, route, 15));
        executorService.submit(secondBus);
        Thread thirdBus = new Thread(new Bus(3, route, 25));
        executorService.submit(thirdBus);

        executorService.shutdown();
    }
}
