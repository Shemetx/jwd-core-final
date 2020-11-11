package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.strategy.FillFromFileStrategy;
import com.epam.jwd.core_final.strategy.impl.FillFromManyLineFile;
import com.epam.jwd.core_final.strategy.impl.FillFromOneLineFile;

import java.util.ArrayList;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {

    private static NassaContext instance = null;

    private NassaContext() {

    }

    public static NassaContext getInstance() {
        if (instance == null) {
            instance = new NassaContext();
        }
        return instance;
    }

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<FlightMission> flightMissions = new ArrayList<>();

    private ApplicationProperties applicationProperties = ApplicationProperties.getSingleton();
    private EntityFactory<Spaceship> spaceshipFactory = SpaceshipFactory.getInstance();

    private FillFromFileStrategy fillFromOneLineFile = FillFromOneLineFile.getInstance();
    private FillFromFileStrategy fillFromManyLineFile = FillFromManyLineFile.getInstance();


    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        if (tClass.getName().equals(CrewMember.class.getName()))
            return (Collection<T>) crewMembers;
        if (tClass.getName().equals(Spaceship.class.getName()))
            return (Collection<T>) spaceships;
        if (tClass.getName().equals(FlightMission.class.getName()))
            return (Collection<T>) flightMissions;
        return null;
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        fillFromOneLineFile.fillCollection(applicationProperties.getInputRootDir(),
                applicationProperties.getCrewFileName());

        fillFromManyLineFile.fillCollection(applicationProperties.getInputRootDir(),
                applicationProperties.getSpaceshipsFileName());
    }


}
