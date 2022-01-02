package com.epam.routes.main;

import com.epam.routes.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        String busesJsonPath = "src/main/java/resources/buses.json";
        String busStopsJsonPath = "src/main/java/resources/busStops.json";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BusWrapper busWrapper = objectMapper.readValue(new File(busesJsonPath), BusWrapper.class);
            List<Bus> buses = busWrapper.getBuses();

            BusStopWrapper busStopWrapper = objectMapper.readValue(new File(busStopsJsonPath), BusStopWrapper.class);
            List<BusStop> busStops = busStopWrapper.getBusStops();

            Route route = Route.getInstance();
            route.setName("Main Route");
            route.setBusStops(busStops);

            ExecutorService executorService = Executors.newFixedThreadPool(buses.size());
            buses.forEach(executorService::submit);
            executorService.shutdown();
        } catch (IOException e) {
            LOGGER.error("IOException caught.", e);
        }


    }
}
