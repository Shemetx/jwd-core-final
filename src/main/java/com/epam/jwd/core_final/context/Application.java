package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.util.PropertyReaderUtil;
import com.epam.jwd.core_final.util.ReInitController;

import java.io.IOException;
import java.util.function.Supplier;

public interface Application {

    static ApplicationMenu start() throws InvalidStateException, IOException {
        PropertyReaderUtil.loadProperties();
        final Supplier<ApplicationContext> applicationContextSupplier = NassaContext::getInstance; // todo
        final NassaContext nassaContext = NassaContext.getInstance();
        nassaContext.init();
        afterContextInit(applicationContextSupplier::get);

        return applicationContextSupplier::get;
    }

    private static void afterContextInit(ApplicationMenu applicationMenu) {
        ReInitController reInitController = ReInitController.getInstance();
        int choose = Integer.MAX_VALUE;
        while (choose != 0) {
            reInitController.checkForReInit();
            applicationMenu.printAvailableOptions();
            choose = applicationMenu.handleUserInput();
            switch (choose) {
                case 1:
                    CrewMembersMenu.crewMembersMenu();
                    break;
                case 2:
                    SpaceshipsMenu.spaceshipsMenu();
                    break;
                case 3:
                    MissionMenu.missionMenu();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }
    }
}
