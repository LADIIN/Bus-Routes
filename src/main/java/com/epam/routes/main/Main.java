package com.epam.routes.main;

import com.epam.routes.entity.Bus;
import com.epam.routes.entity.BusStop;
import com.epam.routes.entity.Route;
import com.epam.routes.reader.JsonReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        JsonReader jsonReader = new JsonReader();

        String busesJson = jsonReader.read("src/main/java/resources/buses.json");
        String routesJSon = jsonReader.read("src/main/java/resources/busStops.json");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Bus> buses = objectMapper.readValue(busesJson, new TypeReference<List<Bus>>() {
            });

            List<BusStop> busStops = objectMapper.readValue(routesJSon, new TypeReference<List<BusStop>>() {
            });

            Route route = new Route("Route", busStops);

            ExecutorService executorService = Executors.newFixedThreadPool(buses.size());
            buses.forEach(bus -> bus.setRoute(route));
            buses.forEach(executorService::submit);
            executorService.shutdown();
        } catch (IOException e) {
            LOGGER.error("IOException caught.", e);
        }


    }
}
