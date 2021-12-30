package com.epam.routes.util;

public class IdGenerator {
    private static long counter;

    private IdGenerator() {
    }

    public static long generatorId() {
        return ++counter;
    }
}
