package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.MissionMenuService;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.util.InputService;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class MissionMenuServiceImpl implements MissionMenuService {
    private MissionService missionService = MissionServiceImpl.getInstance();
    private static MissionMenuServiceImpl instance = null;
    private static Logger logger = Logger.getLogger(MissionMenuServiceImpl.class.getName());

    private MissionMenuServiceImpl() {

    }

    public static MissionMenuServiceImpl getInstance() {
        if (instance == null) {
            instance = new MissionMenuServiceImpl();
        }
        ;
        return instance;
    }

    @Override
    public List<FlightMission> getAll() {
        return missionService.findAllMissions();
    }

    @Override
    public List<FlightMission> getAllByCriteria() {
        Criteria<FlightMission> flightMissionCriteria = MissionCriteriaService.createCriteria();
        return missionService.findAllMissionsByCriteria(flightMissionCriteria);
    }

    @Override
    public FlightMission getFirstByCriteria() {
        Criteria<FlightMission> oneMissionCriteria = MissionCriteriaService.createCriteria();
        Optional<FlightMission> mission = missionService.findMissionByCriteria(oneMissionCriteria);
        if (mission.isPresent()) {
            return mission.get();
        } else {
            throw new UnknownEntityException("Mission wasn't found");
        }
    }

    @Override
    public FlightMission update() {
        FlightMission flightMission = getFirstByCriteria();
        return missionService.updateSpaceshipDetails(flightMission);
    }

    @Override
    public FlightMission createMission() {

            System.out.println("Enter distance of mission: ");
            Long distance = (long) InputService.getInt();
            Spaceship spaceship = InputService.getSpaceshipByDistance(distance);
            Map<Role, Short> spaceshipCrew = spaceship.getCrew();
            List<CrewMember> crewMemberList = InputService.getCrewMembersList(spaceshipCrew, true);
            MissionResult missionResult = InputService.getMissionResult();
            System.out.println("Enter the name of mission: ");
            String name = InputService.getString();
            LocalDateTime startDate = InputService.getDate();
            startDate = startDate.plusDays((int) (Math.random() * 5) + 1);
            LocalDateTime endDate = InputService.getDate();
            endDate = endDate.plusWeeks((int) (Math.random() * 10) + 5);
            return missionService.createMission(name, startDate, endDate, distance, spaceship, crewMemberList, missionResult);
    }


    @Override
    public FlightMission writeToFile() {
        FlightMission flightMission = InputService.getFlightMission();
        ApplicationProperties applicationProperties = ApplicationProperties.getSingleton();
        String path = "src/main/resources/" + applicationProperties.getOutputRootDir()
                + "/" + applicationProperties.getMissionsFileName() + flightMission.getName() + ".json";
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try {
            writer.writeValue(file, flightMission);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flightMission;
    }
}
