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
        run();
    }

    private static void run() {
        String busesJsonPath = "src/main/java/resources/buses.json";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BusWrapper busWrapper = objectMapper.readValue(new File(busesJsonPath), BusWrapper.class);
            List<Bus> buses = busWrapper.getBuses();

            ExecutorService executorService = Executors.newFixedThreadPool(buses.size());
            buses.forEach(bus -> executorService.submit(new Thread(bus)));
            executorService.shutdown();
        } catch (IOException e) {
            LOGGER.error("IOException caught.", e);
        }
    }
}
