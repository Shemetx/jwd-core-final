package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.UnknownEntityException;
import com.epam.jwd.core_final.service.CrewService;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.SpaceshipService;
import com.epam.jwd.core_final.service.impl.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

public class InputService {
    private static Logger logger = Logger.getLogger(InputService.class.getName());

    public static int getInt() {
        Scanner reader = new Scanner(System.in);

        while (!reader.hasNextInt()) {
            System.out.println("Wrong Input!");
            reader.next();
        }
        int i = reader.nextInt();
        return i;
    }

    public static String getString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static Role getRole() {
        int i = 1;
        for (Role role : Role.values()) {
            System.out.println(i++ + " - " + role);
        }
        int choose = getInt();
        switch (choose) {
            case 1:
                return Role.MISSION_SPECIALIST;
            case 2:
                return Role.FLIGHT_ENGINEER;
            case 3:
                return Role.PILOT;
            case 4:
                return Role.COMMANDER;
            default:
                break;
        }
        return null;
    }

    public static Rank getRank() {
        int i = 1;
        for (Rank rank : Rank.values()) {
            System.out.println(i++ + " - " + rank);
        }
        int choose = getInt();
        switch (choose) {
            case 1:
                return Rank.TRAINEE;
            case 2:
                return Rank.SECOND_OFFICER;
            case 3:
                return Rank.FIRST_OFFICER;
            case 4:
                return Rank.CAPTAIN;
            default:
                break;
        }
        return null;
    }

    public static Boolean isReadyForNextMission() {
        System.out.println("1 - True \n 2 - False");
        int choose = getInt();
        switch (choose) {
            case 1:
                return true;
            case 2:
                return false;
            default:
                break;
        }
        return null;
    }

    public static Map<Role, Short> getRoleShortMap() {
        Map<Role, Short> map = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            Role role = getRole();
            System.out.println("Choose number of crew members: ");
            Short count = (short) getInt();
            map.put(role, count);
        }
        return map;
    }

    public static LocalDateTime getDate() {
        ApplicationProperties applicationProperties = ApplicationProperties.getSingleton();
        DateTimeFormatter format = DateTimeFormatter.ofPattern(applicationProperties.getDateTimeFormat());
        LocalDateTime today = LocalDateTime.now();
        String now = today.format(format);
        today = LocalDateTime.parse(now, format);

        return today;
    }


    public static MissionResult getMissionResult() {
        int i = 1;
        for (MissionResult result : MissionResult.values()) {
            System.out.println(i++ + " - " + result);
        }

        int choose = getInt();
        switch (choose) {
            case 1:
                return MissionResult.CANCELLED;
            case 2:
                return MissionResult.FAILED;
            case 3:
                return MissionResult.PLANNED;
            case 4:
                return MissionResult.IN_PROGRESS;
            case 5:
                return MissionResult.COMPLETED;
            default:
                break;
        }
        return null;
    }

    public static Spaceship getSpaceshipByDistance(Long distance) {
        Criteria<Spaceship> spaceshipLong = ShipCriteriaService.getCriteriaForNewMission(distance);
        SpaceshipService spaceshipService = SpaceshipServiceImpl.getInstance();
        List<Spaceship> spaceshipList = spaceshipService.findAllSpaceshipsByCriteria(spaceshipLong);
        if (spaceshipList.isEmpty()) {
            throw new UnknownEntityException("Spaceship not found");
        }
        spaceshipList.forEach(System.out::println);
        System.out.println("Choose by id: ");
        Long shipChoose = (long) InputService.getInt();
        Criteria<Spaceship> spaceshipID = ShipCriteriaService.getCriteriaById(shipChoose);
        Optional<Spaceship> spaceship = spaceshipService.findSpaceshipByCriteria(spaceshipID);
        if (spaceship.isPresent())
            return spaceship.get();
        else {
            throw new UnknownEntityException("Spaceship not found");
        }
    }

    public static List<CrewMember> getCrewMembersList(Map<Role, Short> roleShortMap, Boolean isReady) {
        short countSpecialist = roleShortMap.get(Role.MISSION_SPECIALIST);
        short countEngineer = roleShortMap.get(Role.FLIGHT_ENGINEER);
        short countPilot = roleShortMap.get(Role.PILOT);
        short countCommander = roleShortMap.get(Role.COMMANDER);

        List<CrewMember> specialistList = getCrewRole(Role.MISSION_SPECIALIST, countSpecialist, isReady);
        List<CrewMember> engineerList = getCrewRole(Role.FLIGHT_ENGINEER, countEngineer, isReady);
        List<CrewMember> pilotList = getCrewRole(Role.PILOT, countPilot, isReady);
        List<CrewMember> commanderList = getCrewRole(Role.COMMANDER, countCommander, isReady);

        List<CrewMember> finalList = new ArrayList<>();

        finalList.addAll(specialistList);
        finalList.addAll(engineerList);
        finalList.addAll(pilotList);
        finalList.addAll(commanderList);
        return finalList;
    }

    public static FlightMission getFlightMission() {
        MissionService missionService = MissionServiceImpl.getInstance();
        List<FlightMission> missionList = missionService.findAllMissions();
        missionList.forEach(System.out::println);
        System.out.println("Choose by id: ");
        Long missionChoose = (long) InputService.getInt();
        Criteria<FlightMission> flightMissionCriteria1 = MissionCriteriaService.getCriteriaById(missionChoose);
        Optional<FlightMission> flightMission = missionService.findMissionByCriteria(flightMissionCriteria1);
        return flightMission.get();
    }

    private static List<CrewMember> getCrewRole(Role role, short count, Boolean isReady) {
        CrewService crewService = CrewServiceImpl.getInstance();
        Criteria<CrewMember> crewMemberCriteria = CrewCriteriaService.getCriteriaByRole(role, isReady);
        List<CrewMember> crewMembers = crewService.findAllCrewMembersByCriteria(crewMemberCriteria);
        List<CrewMember> finalList = new ArrayList<>();
        crewMembers.forEach(System.out::println);
        System.out.println("Choose " + count + " crew members:");
        for (; count > 0; count--) {
            Long id = (long) getInt();
            Criteria<CrewMember> idCriteria = CrewCriteriaService.getCriteriaById(id);
            Optional<CrewMember> crewMember = crewService.findCrewMemberByCriteria(idCriteria);
            finalList.add(crewMember.get());
        }
        return finalList;
    }

}
