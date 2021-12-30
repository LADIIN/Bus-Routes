package com.epam.routes.reader;


import com.epam.routes.main.Main;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public String read(String path) {
        String json = null;

        try {
            json = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            LOGGER.info("Can't read Json file.", e);
        }

        return json;
    }
}
