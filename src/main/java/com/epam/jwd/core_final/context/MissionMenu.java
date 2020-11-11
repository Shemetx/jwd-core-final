package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.MissionMenuService;
import com.epam.jwd.core_final.service.impl.MissionMenuServiceImpl;
import com.epam.jwd.core_final.util.InputService;

import java.util.List;
import java.util.logging.Logger;

public class MissionMenu {
    private static Logger logger = Logger.getLogger(MissionMenu.class.getName());

    public static void missionMenu() {
        MissionMenuService missionMenuService = MissionMenuServiceImpl.getInstance();
        int choose = Integer.MAX_VALUE;
        while (choose != 0) {
            printAvailableOptions();
            System.out.println("Choose menu item: ");
            choose = InputService.getInt();
            switch (choose) {
                case 1:
                    logger.info("Try to show all missions");
                    List<FlightMission> flightMissionList = missionMenuService.getAll();
                    flightMissionList.forEach(System.out::println);
                    logger.info("End of show all missions");
                    break;
                case 2:
                    logger.info("Try to find mission list by criteria");
                    List<FlightMission> flightMissions = missionMenuService.getAllByCriteria();
                    flightMissions.forEach(System.out::println);
                    logger.info("End of find mission list by criteria");
                    break;
                case 3:
                    logger.info("Try to find mission by criteria");
                    try {
                        FlightMission flightMission = missionMenuService.getFirstByCriteria();
                        int randomResult = (int) ((Math.random() * 5) + 1);
                        MissionResult[] missionResults = MissionResult.values();
                        flightMission.setMissionResult(missionResults[randomResult]);
                        System.out.println(flightMission);
                    } catch (UnknownEntityException e) {
                        logger.info("Mission by criteria wasn't found");
                        System.out.println(e.getMessage());
                    }
                    logger.info("End of find mission by criteria");
                    break;
                case 4:
                    logger.info("Try to update mission");
                    try {
                        FlightMission updatedMission = missionMenuService.update();
                        System.out.println(updatedMission);
                    } catch (UnknownEntityException e) {
                        logger.info("Mission wasn't found");
                        System.out.println(e.getMessage());
                    }
                    logger.info("End of update mission");
                    break;
                case 5:
                    logger.info("Try to create mission");
                    try {
                        FlightMission createdMission = missionMenuService.createMission();
                        System.out.println(createdMission);
                    } catch (UnknownEntityException e ) {
                        logger.info("Mission didn't created");
                        System.out.println(e.getMessage());
                    }
                    logger.info("End of create mission");
                    break;
                case 6:
                    logger.info("Try to write mission to file");
                    FlightMission missionToWrite = missionMenuService.writeToFile();
                    System.out.println(missionToWrite);
                    logger.info("End of write mission to file");
                    break;
                case 0:
                    break;
                default:
                    break;

            }
        }
    }

    private static void printAvailableOptions() {
        System.out.println("1 - Show all missions");
        System.out.println("2 - Find all missions by criteria");
        System.out.println("3 - Find mission by criteria");
        System.out.println("4 - Update mission");
        System.out.println("5 - Create mission");
        System.out.println("6 - Write mission to file");
        System.out.println("0 - Back to first menu");
    }
}
