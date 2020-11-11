package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.domain.FlightMission;

import java.util.List;

public interface MissionMenuService {
    List<FlightMission> getAll();

    List<FlightMission> getAllByCriteria();

    FlightMission getFirstByCriteria();

    FlightMission update();

    FlightMission createMission();

    FlightMission writeToFile();
}
