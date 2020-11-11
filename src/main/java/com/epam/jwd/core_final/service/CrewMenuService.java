package com.epam.jwd.core_final.service;

import com.epam.jwd.core_final.domain.CrewMember;

import java.util.List;

public interface CrewMenuService {
    List<CrewMember> getAll();

    List<CrewMember> getAllByCriteria();

    CrewMember getFirstByCriteria();

    CrewMember update();
}
