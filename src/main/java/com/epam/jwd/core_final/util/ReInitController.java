package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.logging.Logger;

public class ReInitController {
    private static Logger logger = Logger.getLogger(ReInitController.class.getName());
    private static ReInitController instance = null;
    private final long dirSpace;
    private LocalDateTime timeAfterUpdate;

    private ReInitController() {
        this.dirSpace = getDirSpace();
        this.timeAfterUpdate = LocalDateTime.now();
    }

    public static ReInitController getInstance() {
        if (instance == null) {
            instance = new ReInitController();
        }
        return instance;
    }

    public void checkForReInit() {
        ApplicationProperties applicationProperties = ApplicationProperties.getSingleton();
        LocalDateTime timeToUpdate = timeAfterUpdate.plusMinutes(applicationProperties.getFileRefreshRate());
        if (timeToUpdate.isBefore(LocalDateTime.now())) {
            timeAfterUpdate = timeToUpdate;
            if (dirSpace != getDirSpace()) {
                logger.info("Re initialization storages from files");
                NassaContext nassaContext = NassaContext.getInstance();
                Collection<CrewMember> crewMembers = nassaContext.retrieveBaseEntityList(CrewMember.class);
                Collection<Spaceship> spaceships = nassaContext.retrieveBaseEntityList(Spaceship.class);

                crewMembers.clear();
                spaceships.clear();

                try {
                    nassaContext.init();
                } catch (InvalidStateException e) {
                    e.printStackTrace();
                }

                logger.info("End of re initialization from files");
            }
        }
    }

    private long getDirSpace() {
        ApplicationProperties applicationProperties = ApplicationProperties.getSingleton();
        String path = "src/main/resources/" + applicationProperties.getInputRootDir();
        File inputDir = new File(path);
        return inputDir.getUsableSpace();
    }
}
