package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.DuplicateEntityException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.SpaceshipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.util.InputService;

import java.util.*;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {

    private NassaContext nassaContext = NassaContext.getInstance();
    private Collection<Spaceship> spaceships = nassaContext.retrieveBaseEntityList(Spaceship.class);
    private EntityFactory<Spaceship> spaceshipFactory = SpaceshipFactory.getInstance();
    private static SpaceshipServiceImpl instance = null;

    private SpaceshipServiceImpl() {

    }

    public static SpaceshipServiceImpl getInstance() {
        if (instance == null) {
            instance = new SpaceshipServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return
                new ArrayList<>(spaceships);
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        SpaceshipCriteria spaceshipCriteria = ((SpaceshipCriteria) criteria);
        return spaceships
                .stream()
                .filter(spaceship -> {
                    String name = spaceshipCriteria.getName();
                    if (name != null) {
                        return spaceship.getName().equals(name);
                    }
                    return true;
                })
                .filter(spaceship -> {
                    Long id = spaceshipCriteria.getId();
                    if (id != null) {
                        return spaceship.getId().equals(id);
                    }
                    return true;
                })
                .filter(spaceship -> {
                    Map<Role, Short> crew = spaceshipCriteria.getCrew();
                    if (crew != null) {
                        return spaceship.getCrew().equals(crew);
                    }
                    return true;
                })
                .filter(spaceship -> {
                    Long distance = spaceshipCriteria.getFlightDistance();
                    if (distance != null) {
                        return spaceship.getFlightDistance() <= distance;
                    }
                    return true;
                })
                .filter(spaceship -> {
                    Boolean isReadyForNextMissions = spaceshipCriteria.getReadyForNextMissions();
                    if (isReadyForNextMissions != null) {
                        return spaceship.getReadyForNextMissions().equals(isReadyForNextMissions);
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        SpaceshipCriteria spaceshipCriteria = ((SpaceshipCriteria) criteria);
        return spaceships
                .stream()
                .filter(spaceship -> {
                    String name = spaceshipCriteria.getName();
                    if (name != null) {
                        return spaceship.getName().equals(name);
                    }
                    return true;
                })
                .filter(spaceship -> {
                    Long id = spaceshipCriteria.getId();
                    if (id != null) {
                        return spaceship.getId().equals(id);
                    }
                    return true;
                })
                .filter(spaceship -> {
                    Map<Role, Short> crew = spaceshipCriteria.getCrew();
                    if (crew != null) {
                        return spaceship.getCrew().equals(crew);
                    }
                    return true;
                })
                .filter(spaceship -> {
                    Long distance = spaceshipCriteria.getFlightDistance();
                    if (distance != null) {
                        return spaceship.getFlightDistance().equals(distance);
                    }
                    return true;
                })
                .filter(spaceship -> {
                    Boolean isReadyForNextMissions = spaceshipCriteria.getReadyForNextMissions();
                    if (isReadyForNextMissions != null) {
                        return spaceship.getReadyForNextMissions().equals(isReadyForNextMissions);
                    }
                    return true;
                })
                .findFirst();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        printAvailableOptions();
        System.out.println("Choose menu item: ");
        int choose = InputService.getInt();
        switch (choose) {
            case 1:
                Long id = (long) InputService.getInt();
                spaceship.setId(id);
                break;
            case 2:
                String name = InputService.getString();
                spaceship.setName(name);
                break;
            case 3:
                Map<Role, Short> map = InputService.getRoleShortMap();
                spaceship.setCrew(map);
                break;
            case 4:
                Long flightDistance = (long) InputService.getInt();
                spaceship.setFlightDistance(flightDistance);
                break;
            case 5:
                Boolean isReady = InputService.isReadyForNextMission();
                spaceship.setReadyForNextMissions(isReady);
                break;
            default:
                break;
        }
        return spaceship;
    }

    private static void printAvailableOptions() {
        System.out.println("Choose what you want ti update: ");
        System.out.println("1 - ID");
        System.out.println("2 - Name");
        System.out.println("3 - Crew");
        System.out.println("4 - Flight distance");
        System.out.println("5 - is Ready for next mission");
        System.out.println("0 - Stop to choose");
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship) throws RuntimeException {
        spaceship.setReadyForNextMissions(false);
    }

    @Override
    public Spaceship createSpaceship(Long id, String name, Map<Role, Short> roleShortMap, Long distance) throws RuntimeException {
        Spaceship spaceship = spaceshipFactory.create(id, name, roleShortMap, distance);
        try {
            if (spaceships.stream().anyMatch(spaceship::equals)) {
                throw new DuplicateEntityException("Crew member is already consist in storage");
            }
            spaceships.add(spaceship);
        } catch (DuplicateEntityException e) {
            System.out.println(e.getMessage());
        }
        return spaceship;
    }
}
