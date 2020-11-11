package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.epam.jwd.core_final.strategy.FillFromFileStrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FillFromManyLineFile implements FillFromFileStrategy {
    private static FillFromManyLineFile instance = null;
    private static Logger logger = Logger.getLogger(FillFromManyLineFile.class.getName());

    private FillFromManyLineFile() {

    }

    public static FillFromManyLineFile getInstance() {
        if (instance == null) {
            instance = new FillFromManyLineFile();
        }
        return instance;
    }

    @Override
    public void fillCollection(String root, String fileName) {
        logger.info("Start to fill storage from " + fileName);
        String path = "src/main/resources/" + root
                + "/" + fileName;
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (!line.contains("#"))
                    stringWork(line, count++);
            }
            logger.info("End of filling storage from " + fileName);
        } catch (IOException e) {
            logger.log(Level.WARNING, "File not found: " + fileName);
            throw new InvalidStateException(e.getMessage(), e);
        }
    }

    private void stringWork(String line, int countCrew) {
        SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
        String[] list = line.split("[{},:;]");
        long distance = Long.parseLong(list[1]);
        Map<Role, Short> map = new HashMap<>();
        try {
            for (int i = 3, j = 4; i < 10; i += 2, j += 2) {
                int roleID = Integer.parseInt(list[i]);
                short count = Short.parseShort(list[j]);
                Role role = Role.resolveRoleById(roleID);
                map.put(role, count);
            }
            spaceshipService.createSpaceship((long) countCrew, list[0], map, distance);
        } catch (UnknownEntityException e) {
            logger.info("File has an incorrect role, distance: " + distance);
            System.out.println(e.getMessage());
        }
    }
}
