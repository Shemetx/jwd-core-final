package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.DuplicateEntityException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.util.InputService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {

    private NassaContext nassaContext = NassaContext.getInstance();
    private Collection<CrewMember> crewMembers = nassaContext.retrieveBaseEntityList(CrewMember.class);
    private EntityFactory<CrewMember> crewFactory = CrewMemberFactory.getInstance();
    private static CrewServiceImpl instance = null;

    private CrewServiceImpl() {

    }

    public static CrewServiceImpl getInstance() {
        if (instance == null) {
            instance = new CrewServiceImpl();
        }
        ;
        return instance;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return
                new ArrayList<>(crewMembers);
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        CrewMemberCriteria crewMemberCriteria = ((CrewMemberCriteria) criteria);
        return crewMembers
                .stream()
                .filter(crewMember -> {
                    String name = crewMemberCriteria.getName();
                    if (name != null) {
                        return crewMember.getName().equals(name);
                    }
                    return true;
                })
                .filter(crewMember -> {
                    Long id = crewMemberCriteria.getId();
                    if (id != null) {
                        return crewMember.getName().equals(id);
                    }
                    return true;
                })
                .filter(crewMember -> {
                    Role role = crewMemberCriteria.getRole();
                    if (role != null) {
                        return crewMember.getRole().equals(role);
                    }
                    return true;
                })
                .filter(crewMember -> {
                    Rank rank = crewMemberCriteria.getRank();
                    if (rank != null) {
                        return crewMember.getRank().equals(rank);
                    }
                    return true;
                })
                .filter(crewMember -> {
                    Boolean isReadyForNextMissions = crewMemberCriteria.getIsReadyForNextMissions();
                    if (isReadyForNextMissions != null) {
                        return crewMember.getReadyForNextMissions().equals(isReadyForNextMissions);
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        CrewMemberCriteria crewMemberCriteria = ((CrewMemberCriteria) criteria);
        return crewMembers
                .stream()
                .filter(crewMember -> {
                    String name = crewMemberCriteria.getName();
                    if (name != null) {
                        return crewMember.getName().equals(name);
                    }
                    return true;
                })
                .filter(crewMember -> {
                    Long id = crewMemberCriteria.getId();
                    if (id != null) {
                        return crewMember.getId().equals(id);
                    }
                    return true;
                })
                .filter(crewMember -> {
                    Role role = crewMemberCriteria.getRole();
                    if (role != null) {
                        return crewMember.getRole().equals(role);
                    }
                    return true;
                })
                .filter(crewMember -> {
                    Rank rank = crewMemberCriteria.getRank();
                    if (rank != null) {
                        return crewMember.getRank().equals(rank);
                    }
                    return true;
                })
                .filter(crewMember -> {
                    Boolean isReadyForNextMissions = crewMemberCriteria.getIsReadyForNextMissions();
                    if (isReadyForNextMissions != null) {
                        return crewMember.getReadyForNextMissions().equals(isReadyForNextMissions);
                    }
                    return true;
                })
                .findFirst();

    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        printAvailableOptions();
        System.out.println("Choose menu item: ");
        int choose = InputService.getInt();
        switch (choose) {
            case 1:
                Long id = (long) InputService.getInt();
                crewMember.setId(id);
                break;
            case 2:
                String name = InputService.getString();
                crewMember.setName(name);
                break;
            case 3:
                Role role = InputService.getRole();
                crewMember.setRole(role);
                break;
            case 4:
                Rank rank = InputService.getRank();
                crewMember.setRank(rank);
                break;
            case 5:
                Boolean isReady = InputService.isReadyForNextMission();
                crewMember.setReadyForNextMissions(isReady);
                break;
            default:
                break;
        }
        return crewMember;
    }

    private static void printAvailableOptions() {
        System.out.println("Choose you want to update: ");
        System.out.println("1 - ID");
        System.out.println("2 - Name");
        System.out.println("3 - Role");
        System.out.println("4 - Rank");
        System.out.println("5 - is Ready for next mission");
        System.out.println("0 - Stop to choose");
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember) throws RuntimeException {
        crewMember.setReadyForNextMissions(false);
    }

    @Override
    public CrewMember createCrewMember(Long id, String name, Role role, Rank rank) throws RuntimeException, DuplicateEntityException {
        CrewMember crewMember = crewFactory.create(id, name, role, rank);
        try {
            if (crewMembers.stream().anyMatch(crewMember::equals)) {
                throw new DuplicateEntityException("Crew member is already consist in storage");
            }
            crewMembers.add(crewMember);
        } catch (DuplicateEntityException e) {
            System.out.println(e.getMessage());
        }
        return crewMember;
    }
}
