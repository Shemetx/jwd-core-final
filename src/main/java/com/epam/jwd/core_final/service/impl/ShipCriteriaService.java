package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.util.InputService;

import java.util.Map;

public class ShipCriteriaService {

    public static Criteria<Spaceship> createCriteria() {
        Long id = null;
        String name = null;
        Map<Role, Short> roleShortMap = null;
        Long flightDistance = null;
        Boolean isReady = null;
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
                    System.out.println("Choose crew: ");
                    roleShortMap = InputService.getRoleShortMap();
                    break;
                case 4:
                    System.out.println("Choose flight distance: ");
                    flightDistance = (long) InputService.getInt();
                    break;
                case 5:
                    System.out.println("Choose is ready for next mission: ");
                    isReady = InputService.isReadyForNextMission();
                    break;
                default:
                    break;

            }
        }
        Long finalId = id;
        String finalName = name;
        Map<Role, Short> finalRoleShortMap = roleShortMap;
        Long finalFlightDistance = flightDistance;
        Boolean finalIsReady = isReady;

        return new SpaceshipCriteria.SpaceshipBuilder() {{
            id(finalId);
            name(finalName);
            crew(finalRoleShortMap);
            flightDistance(finalFlightDistance);
            isReadyForNextMissions(finalIsReady);
        }}.build();
    }

    public static Criteria<Spaceship> getCriteriaForNewMission(Long distance) {
        return new SpaceshipCriteria.SpaceshipBuilder() {{
            flightDistance(distance);
            isReadyForNextMissions(true);
        }}.build();
    }

    public static Criteria<Spaceship> getCriteriaById(Long id) {
        return new SpaceshipCriteria.SpaceshipBuilder() {{
            id(id);
        }}.build();
    }

    private static void printAvailableOptions() {
        System.out.println("Choose which criteria you want: ");
        System.out.println("1 - ID");
        System.out.println("2 - Name");
        System.out.println("3 - Crew");
        System.out.println("4 - Flight distance");
        System.out.println("5 - is Ready for next mission");
        System.out.println("0 - Stop to choose");
    }
}
