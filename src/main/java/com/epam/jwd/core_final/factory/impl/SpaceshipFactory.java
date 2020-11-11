package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.util.Map;

public class SpaceshipFactory implements EntityFactory<Spaceship> {
    private static SpaceshipFactory instance = null;

    private SpaceshipFactory() {

    }

    public static SpaceshipFactory getInstance() {
        if (instance == null) {
            instance = new SpaceshipFactory();
        }
        return instance;
    }

    @Override
    public Spaceship create(Object... args) {
        return new Spaceship((long) args[0], (String) args[1], (Map<Role, Short>) args[2],
                (Long) args[3]);
    }
}
