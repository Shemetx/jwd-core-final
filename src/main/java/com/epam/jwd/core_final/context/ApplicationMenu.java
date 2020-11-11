package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.util.InputService;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default void printAvailableOptions() {
        System.out.println("Choose which one you want to work: ");
        System.out.println("1 - Crew Members");
        System.out.println("2 - Spaceships");
        System.out.println("3 - Flight Missions");
        System.out.println("0 - Exit program");
    }

    default int handleUserInput() {
        int userInput = InputService.getInt();
        return userInput;
    }
}
