package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.ShipMenuService;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.List;
import java.util.Optional;

public class ShipMenuServiceImpl implements ShipMenuService {
    private SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
    private static ShipMenuServiceImpl instance = null;

    private ShipMenuServiceImpl() {

    }

    public static ShipMenuServiceImpl getInstance() {
        if (instance == null) {
            instance = new ShipMenuServiceImpl();
        }
        ;
        return instance;
    }


    @Override
    public List<Spaceship> getAll() {
        return spaceshipService.findAllSpaceships();
    }

    @Override
    public List<Spaceship> getAllByCriteria() {
        Criteria<Spaceship> spaceshipsCriteria = ShipCriteriaService.createCriteria();
        return spaceshipService.findAllSpaceshipsByCriteria(spaceshipsCriteria);
    }

    @Override
    public Spaceship getFirstByCriteria() {
        Criteria<Spaceship> spaceshipCriteria = ShipCriteriaService.createCriteria();
        Optional<Spaceship> spaceship = spaceshipService.findSpaceshipByCriteria(spaceshipCriteria);
        if (spaceship.isPresent())
            return spaceship.get();
        else {
            throw new UnknownEntityException("Spaceship not found");
        }
    }

    @Override
    public Spaceship update() {
        Spaceship spaceship = getFirstByCriteria();
        return spaceshipService.updateSpaceshipDetails(spaceship);
    }
}
