package com.epam.routes.reader;


import com.epam.routes.main.Main;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(Main.class.getName());

    public String read(String path) {
        String json = null;

        try {
            json = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Can't read Json file.", e);
        }

        return json;
    }
}
