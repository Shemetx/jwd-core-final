package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDateTime;
import java.util.List;

public class FlightMissionFactory implements EntityFactory {
    private static FlightMissionFactory instance = null;

    private FlightMissionFactory() {

    }

    public static FlightMissionFactory getInstance() {
        if (instance == null) {
            instance = new FlightMissionFactory();
        }
        return instance;
    }

    @Override
    public BaseEntity create(Object... args) {
        return new FlightMission((Long) args[0], (String) args[1], (LocalDateTime) args[2], (LocalDateTime) args[3],
                (long) args[4], (Spaceship) args[5], (List<CrewMember>) args[6], (MissionResult) args[7]);
    }
}
