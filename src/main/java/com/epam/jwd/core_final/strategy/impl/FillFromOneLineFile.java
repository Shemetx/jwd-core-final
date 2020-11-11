package com.epam.jwd.core_final.strategy.impl;

import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.strategy.FillFromFileStrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FillFromOneLineFile implements FillFromFileStrategy {

    private static FillFromOneLineFile instance = null;
    private static Logger logger = Logger.getLogger(FillFromOneLineFile.class.getName());
    private FillFromOneLineFile() {

    }

    public static FillFromOneLineFile getInstance() {
        if (instance == null) {
            instance = new FillFromOneLineFile();
        }
        return instance;
    }

    @Override
    public void fillCollection(String root,String fileName) {
        logger.info("Start to fill storage from " + fileName);
        String path = "src/main/resources/" + root
                + "/" + fileName;
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null) {
                if (!line.contains("#"))
                    stringWork(line);
            }
            logger.info("End of filling storage from " + fileName);
        } catch (IOException e) {
            logger.log(Level.WARNING,"File not found: " + fileName);
            throw new InvalidStateException(e.getMessage(),e);
        }
    }

    private void stringWork(String line) {
        CrewService crewService = CrewServiceImpl.getInstance();
        String[] list = line.split("[;,]");
        int count = 0;
        for (int i = 0;i < list.length; i+=3) {
            int roleID = Integer.parseInt(list[i]);
            int rankID = Integer.parseInt(list[i+2]);
            try {
                Role role = Role.resolveRoleById(roleID);
                Rank rank = Rank.resolveRankById(rankID);
                crewService.createCrewMember((long) count++,list[i+1], role, rank);
            } catch (UnknownEntityException e) {
                logger.info("No such role or rank by id");
                System.out.println(e.getMessage());
            }
        }
    }
}
