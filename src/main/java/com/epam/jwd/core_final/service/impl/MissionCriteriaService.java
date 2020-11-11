package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.SpaceshipsMenu;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.ShipMenuService;
import com.epam.jwd.core_final.util.InputService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MissionCriteriaService {
    private static Logger logger = Logger.getLogger(MissionCriteriaService.class.getName());
    public static Criteria<FlightMission> createCriteria() {
        Long id = null;
        String name = null;
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        List<CrewMember> crewMemberList = null;
        Spaceship spaceship = null;
        MissionResult missionResult = null;
        Long flightDistance = null;
        int choose = Integer.MAX_VALUE;
        while (choose != 0) {
            printAvailableOptions();
            System.out.println("Choose menu item: ");
            choose = InputService.getInt();
            switch (choose) {
                case 1:
                    System.out.println("Enter the id: ");
                    id = (long) InputService.getInt();
                    break;
                case 2:
                    System.out.println("Enter the name: ");
                    name = InputService.getString();
                    break;
                case 3:
                    startDate = InputService.getDate();
                    startDate = startDate.plusDays((int) (Math.random() * 5) + 1);
                    break;
                case 4:
                    endDate = InputService.getDate();
                    endDate = endDate.plusWeeks((int) (Math.random() * 10) + 5);
                    break;
                case 5:
                    System.out.println("Enter flight distance: ");
                    flightDistance = (long) InputService.getInt();
                    break;
                case 6:
                    System.out.println("Choose crew: ");
                    Map<Role, Short> crew = InputService.getRoleShortMap();
                    crewMemberList = InputService.getCrewMembersList(crew, null);
                    break;
                case 7:
                    System.out.println("Choose spaceship: ");
                    try {
                        ShipMenuService shipMenuService = ShipMenuServiceImpl.getInstance();
                        spaceship = shipMenuService.getFirstByCriteria();
                    } catch (UnknownEntityException e) {
                        logger.info("Spaceships wasn't found");
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    System.out.println("Choose mission result");
                    missionResult = InputService.getMissionResult();
                    break;
                case 0:
                    break;
                default:
                    break;

            }
        }
        Long finalId = id;
        String finalName = name;
        Long finalFlightDistance = flightDistance;
        LocalDateTime finalStartDate = startDate;
        LocalDateTime finalEndDate = endDate;
        List<CrewMember> finalCrewMemberList = crewMemberList;
        Spaceship finalSpaceship = spaceship;
        MissionResult finalMissionResult = missionResult;
        return new FlightMissionCriteria.FlightMissionBuilder() {{
            id(finalId);
            name(finalName);
            startDate(finalStartDate);
            endDate(finalEndDate);
            distance(finalFlightDistance);
            assignedCrew(finalCrewMemberList);
            assignedSpaceShift(finalSpaceship);
            missionResult(finalMissionResult);
        }}.build();
    }

    public static Criteria<FlightMission> getCriteriaById(Long id) {
        return new FlightMissionCriteria.FlightMissionBuilder() {{
            id(id);
        }}.build();
    }

    private static void printAvailableOptions() {
        System.out.println("Choose criteria you want: ");
        System.out.println("1 - ID");
        System.out.println("2 - Name");
        System.out.println("3 - Start date");
        System.out.println("4 - End date");
        System.out.println("5 - Flight distance");
        System.out.println("6 - Crew");
        System.out.println("7 - Spaceship");
        System.out.println("8 - Mission result");
        System.out.println("0 - Stop to choose");
    }
}
