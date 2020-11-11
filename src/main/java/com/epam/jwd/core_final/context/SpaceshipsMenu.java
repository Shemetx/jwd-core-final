package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.ShipMenuService;
import com.epam.jwd.core_final.service.impl.ShipMenuServiceImpl;
import com.epam.jwd.core_final.util.InputService;

import java.util.List;
import java.util.logging.Logger;

public class SpaceshipsMenu {
    private static Logger logger = Logger.getLogger(SpaceshipsMenu.class.getName());

    public static void spaceshipsMenu() {
        ShipMenuService shipMenuService = ShipMenuServiceImpl.getInstance();
        int choose = Integer.MAX_VALUE;
        while (choose != 0) {
            printAvailableOptions();
            System.out.println("Choose menu item: ");
            choose = InputService.getInt();
            switch (choose) {
                case 1:
                    logger.info("Try to show all spaceships");
                    List<Spaceship> showAll = shipMenuService.getAll();
                    showAll.forEach(System.out::println);
                    logger.info("End of show all spaceships");
                    break;
                case 2:
                    logger.info("Try to found list of spaceships by criteria");
                    List<Spaceship> spaceshipList = shipMenuService.getAllByCriteria();
                    spaceshipList.forEach(System.out::println);
                    logger.info("End of found list of spaceships by criteria");
                    break;
                case 3:
                    try {
                        logger.info("Try to found one spaceship by criteria");
                        Spaceship spaceship = shipMenuService.getFirstByCriteria();
                        System.out.println(spaceship);
                        logger.info("End of found one spaceship by criteria");
                    } catch (UnknownEntityException e) {
                        logger.info("Spaceships wasnt found");
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    logger.info("Try to update spaceship");
                    try {
                        Spaceship updatedSpaceship = shipMenuService.update();
                        System.out.println(updatedSpaceship);
                    } catch (UnknownEntityException e) {
                        logger.info("Spaceship wasn't found");
                        System.out.println(e.getMessage());
                    }
                    logger.info("End of update spaceship");
                    break;
                case 0:
                    break;
                default:
                    break;

            }
        }
    }

    private static void printAvailableOptions() {
        System.out.println("1 - Show all spaceships");
        System.out.println("2 - Find all spaceships by criteria");
        System.out.println("3 - Find spaceship by criteria");
        System.out.println("4 - Update spaceship");
        System.out.println("0 - Back to first menu");
    }
}
