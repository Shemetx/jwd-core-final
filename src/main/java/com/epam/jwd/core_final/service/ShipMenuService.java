package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.domain.Spaceship;

import java.util.List;

public interface ShipMenuService {
    List<Spaceship> getAll();

    List<Spaceship> getAllByCriteria();

    Spaceship getFirstByCriteria();

    Spaceship update();
}
