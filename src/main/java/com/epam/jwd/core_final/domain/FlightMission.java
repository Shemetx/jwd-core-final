package com.epam.jwd.core_final.domain;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 */
public class FlightMission extends AbstractBaseEntity {
    // todo
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long distance;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    public FlightMission(Long id, String name, LocalDateTime startDate,
                         LocalDateTime endDate, Long distance,
                         Spaceship assignedSpaceShift,
                         List<CrewMember> assignedCrew,
                         MissionResult missionResult) {
        super(id, name);
        this.assignedCrew = assignedCrew;
        this.assignedSpaceShift = assignedSpaceShift;
        this.distance = distance;
        this.endDate = endDate;
        this.missionResult = missionResult;
        this.startDate = startDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Long getDistance() {
        return distance;
    }

    public Spaceship getAssignedSpaceShift() {
        return assignedSpaceShift;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public void setAssignedSpaceShift(Spaceship assignedSpaceShift) {
        this.assignedSpaceShift = assignedSpaceShift;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "FlightMission " +
                "ID = " + getId() +
                ", NAME = " + getName() +
                ", START DATE = " + startDate +
                ", END DATE = " + endDate +
                ", DISTANCE = " + distance +
                ", SPACESHIP = " + assignedSpaceShift +
                ", CREW = " + assignedCrew +
                ", RESULT = " + missionResult;

    }
}
