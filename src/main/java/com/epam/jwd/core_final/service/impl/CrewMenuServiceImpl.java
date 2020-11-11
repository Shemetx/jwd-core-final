package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.CrewMembersMenu;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.CrewMenuService;
import com.epam.jwd.core_final.service.CrewService;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CrewMenuServiceImpl implements CrewMenuService {
    private CrewService crewService = CrewServiceImpl.getInstance();
    private static CrewMenuServiceImpl instance = null;
    private static Logger logger = Logger.getLogger(CrewMembersMenu.class.getName());

    private CrewMenuServiceImpl() {

    }

    public static CrewMenuServiceImpl getInstance() {
        if (instance == null) {
            instance = new CrewMenuServiceImpl();
        }
        ;
        return instance;
    }

    @Override
    public List<CrewMember> getAll() {
        return crewService.findAllCrewMembers();
    }

    @Override
    public List<CrewMember> getAllByCriteria() {
        Criteria<CrewMember> crewMembersCriteria = CrewCriteriaService.createCriteria();
        return crewService.findAllCrewMembersByCriteria(crewMembersCriteria);
    }

    @Override
    public CrewMember getFirstByCriteria() {
        Criteria<CrewMember> crewMemberCriteria = CrewCriteriaService.createCriteria();
        Optional<CrewMember> crewMember = crewService.findCrewMemberByCriteria(crewMemberCriteria);
        if (crewMember.isPresent())
            return crewMember.get();
        else {
            throw new UnknownEntityException("Crew member not found");
        }
    }

    @Override
    public CrewMember update() {
        CrewMember crewMemberUpdate = getFirstByCriteria();
        return crewService.updateCrewMemberDetails(crewMemberUpdate);
    }
}
