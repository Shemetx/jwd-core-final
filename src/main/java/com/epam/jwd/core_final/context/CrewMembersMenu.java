package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.CrewMenuService;
import com.epam.jwd.core_final.service.impl.CrewMenuServiceImpl;
import com.epam.jwd.core_final.util.InputService;

import java.util.List;
import java.util.logging.Logger;

public class CrewMembersMenu {
    private static Logger logger = Logger.getLogger(CrewMembersMenu.class.getName());

    public static void crewMembersMenu() {
        CrewMenuService menuService = CrewMenuServiceImpl.getInstance();
        int choose = Integer.MAX_VALUE;
        while (choose != 0) {
            printAvailableOptions();
            System.out.println("Choose menu item: ");
            choose = InputService.getInt();
            switch (choose) {
                case 1:
                    logger.info("Try to show all list of Crew members");
                    List<CrewMember> showAllList = menuService.getAll();
                    showAllList.forEach(System.out::println);
                    logger.info("End of show list of Crew members");
                    break;
                case 2:
                    logger.info("Try to found list of Crew members by criteria");
                    List<CrewMember> showAllByCriteria = menuService.getAllByCriteria();
                    showAllByCriteria.forEach(System.out::println);
                    logger.info("End of found list of Crew members by criteria");
                    break;
                case 3:
                    try {
                        logger.info("Try to found first Crew members by criteria");
                        CrewMember showFirstByCriteria = menuService.getFirstByCriteria();
                        System.out.println(showFirstByCriteria);
                        logger.info("End of found first crew member by criteria");
                    } catch (UnknownEntityException e) {
                        logger.info("Crew member wasn't found");
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    logger.info("Try to update crew member");
                    try {
                        CrewMember updatedCruMember = menuService.update();
                        System.out.println(updatedCruMember);
                    } catch (UnknownEntityException e) {
                        logger.info("Crew member wasn't found");
                        System.out.println(e.getMessage());
                    }
                    logger.info("End of update crew member");
                    break;
                case 0:
                    break;
                default:
                    break;

            }
        }
    }

    private static void printAvailableOptions() {
        System.out.println("1 - Show all crew members");
        System.out.println("2 - Find all crew members by criteria");
        System.out.println("3 - Find crew member by criteria");
        System.out.println("4 - Update crew member");
        System.out.println("0 - Back to first menu");
    }

}
