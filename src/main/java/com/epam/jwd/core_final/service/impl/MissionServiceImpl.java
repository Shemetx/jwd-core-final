package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.criteria.FlightMissionCriteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.ShipMenuService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.util.InputService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MissionServiceImpl implements MissionService {

    private static Logger logger = Logger.getLogger(MissionServiceImpl.class.getName());
    private NassaContext nassaContext = NassaContext.getInstance();
    private Collection<FlightMission> flightMissions = nassaContext.retrieveBaseEntityList(FlightMission.class);
    private EntityFactory<FlightMission> flightMissionFactory = FlightMissionFactory.getInstance();
    private static MissionServiceImpl instance = null;
    private static Long missionId;

    private MissionServiceImpl() {

    }

    public static MissionServiceImpl getInstance() {
        if (instance == null) {
            instance = new MissionServiceImpl();
            missionId = 0L;
        }
        return instance;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return
                new ArrayList<>(flightMissions);
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        FlightMissionCriteria flightMissionCriteria = ((FlightMissionCriteria) criteria);
        return flightMissions
                .stream()
                .filter(flightMission -> {
                    String name = flightMissionCriteria.getName();
                    if (name != null) {
                        return flightMission.getName().equals(name);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    Long id = flightMissionCriteria.getId();
                    if (id != null) {
                        return flightMission.getId().equals(id);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    LocalDateTime startDate = flightMissionCriteria.getStartDate();
                    if (startDate != null) {
                        return flightMission.getStartDate().equals(startDate);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    LocalDateTime endDate = flightMissionCriteria.getEndDate();
                    if (endDate != null) {
                        return flightMission.getEndDate().equals(endDate);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    List<CrewMember> crewMembers = flightMissionCriteria.getAssignedCrew();
                    if (crewMembers != null) {
                        return flightMission.getAssignedCrew().equals(crewMembers);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    Spaceship spaceship = flightMissionCriteria.getAssignedSpaceShift();
                    if (spaceship != null) {
                        return flightMission.getAssignedSpaceShift().equals(spaceship);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    Long distance = flightMissionCriteria.getDistance();
                    if (distance != null) {
                        return flightMission.getDistance().equals(distance);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    MissionResult missionResult = flightMissionCriteria.getMissionResult();
                    if (missionResult != null) {
                        return flightMission.getMissionResult().equals(missionResult);
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        FlightMissionCriteria flightMissionCriteria = ((FlightMissionCriteria) criteria);
        return flightMissions
                .stream()
                .filter(flightMission -> {
                    String name = flightMissionCriteria.getName();
                    if (name != null) {
                        return flightMission.getName().equals(name);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    Long id = flightMissionCriteria.getId();
                    if (id != null) {
                        return flightMission.getId().equals(id);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    LocalDateTime startDate = flightMissionCriteria.getStartDate();
                    if (startDate != null) {
                        return flightMission.getStartDate().equals(startDate);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    LocalDateTime endDate = flightMissionCriteria.getEndDate();
                    if (endDate != null) {
                        return flightMission.getEndDate().equals(endDate);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    List<CrewMember> crewMembers = flightMissionCriteria.getAssignedCrew();
                    if (crewMembers != null) {
                        return flightMission.getAssignedCrew().equals(crewMembers);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    Spaceship spaceship = flightMissionCriteria.getAssignedSpaceShift();
                    if (spaceship != null) {
                        return flightMission.getAssignedSpaceShift().equals(spaceship);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    Long distance = flightMissionCriteria.getDistance();
                    if (distance != null) {
                        return flightMission.getDistance().equals(distance);
                    }
                    return true;
                })
                .filter(flightMission -> {
                    MissionResult missionResult = flightMissionCriteria.getMissionResult();
                    if (missionResult != null) {
                        return flightMission.getMissionResult().equals(missionResult);
                    }
                    return true;
                })
                .findFirst();
    }

    @Override
    public FlightMission updateSpaceshipDetails(FlightMission flightMission) {
        printAvailableOptions();
        System.out.println("Choose menu item: ");
        int choose = InputService.getInt();
        switch (choose) {
            case 1:
                Long id = (long) InputService.getInt();
                flightMission.setId(id);
                break;
            case 2:
                String name = InputService.getString();
                flightMission.setName(name);
                break;
            case 3:
                LocalDateTime startDate = InputService.getDate();
                startDate = startDate.plusDays((int) (Math.random() * 5) + 1);
                flightMission.setStartDate(startDate);
                break;
            case 4:
                LocalDateTime endDate = InputService.getDate();
                endDate = endDate.plusWeeks((int) (Math.random() * 10) + 5);
                flightMission.setEndDate(endDate);
                break;
            case 5:
                Long flightDistance = (long) InputService.getInt();
                flightMission.setDistance(flightDistance);
                break;
            case 6:
                Map<Role, Short> crew = InputService.getRoleShortMap();
                List<CrewMember> crewMemberList = InputService.getCrewMembersList(crew, null);
                flightMission.setAssignedCrew(crewMemberList);
                break;
            case 7:
                try {
                    ShipMenuService shipMenuService = ShipMenuServiceImpl.getInstance();
                    Spaceship spaceship = shipMenuService.getFirstByCriteria();
                    flightMission.setAssignedSpaceShift(spaceship);
                } catch (UnknownEntityException e) {
                    logger.info("Spaceships wasn't found");
                    System.out.println(e.getMessage());
                }
                break;
            case 8:
                MissionResult missionResult = InputService.getMissionResult();
                flightMission.setMissionResult(missionResult);
                break;
            case 0:
                break;
            default:
                break;
        }
        return flightMission;
    }

    private static void printAvailableOptions() {
        System.out.println("Choose what you want to update: ");
        System.out.println("1 - ID");
        System.out.println("2 - Name");
        System.out.println("3 - Start date");
        System.out.println("4 - End date");
        System.out.println("5 - Flight distance");
        System.out.println("6 - Crew");
        System.out.println("7 - Spaceship");
        System.out.println("8 - Mission result");
        System.out.println("0 - Stop to choose");
    }

    @Override
    public FlightMission createMission(String name, LocalDateTime startDate,
                                       LocalDateTime endDate, Long distance,
                                       Spaceship assignedSpaceShift,
                                       List<CrewMember> assignedCrew,
                                       MissionResult missionResult) {
        CrewService crewService = CrewServiceImpl.getInstance();
        SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
        spaceshipService.assignSpaceshipOnMission(assignedSpaceShift);
        for (CrewMember crewMember : assignedCrew) {
            crewService.assignCrewMemberOnMission(crewMember);
        }

        FlightMission flightMission = flightMissionFactory.create(missionId++, name, startDate, endDate, distance,
                assignedSpaceShift, assignedCrew, missionResult);
        flightMissions.add(flightMission);
        return flightMission;
    }
}
