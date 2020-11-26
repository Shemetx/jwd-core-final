package com.epam.jwd.core_final;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.File;
import java.io.IOException;
import java.util.logging.LogManager;

public class Main {

    public static void main(String[] args) throws InvalidStateException, IOException {
        try {
            File file = new File("logs");
            file.mkdir();
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        Application.start();
    }
}

/*
что хранится в метоспейсе

 */