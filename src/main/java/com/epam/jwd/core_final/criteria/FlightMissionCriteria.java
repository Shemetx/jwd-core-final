package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */

public class FlightMissionCriteria extends Criteria<FlightMission> {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long distance;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;
    private MissionResult missionResult;

    private FlightMissionCriteria(Long id, String name, LocalDateTime startDate,
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

    public static class FlightMissionBuilder extends Criteria.Builder {
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private Long distance;
        private Spaceship assignedSpaceShift;
        private List<CrewMember> assignedCrew;
        private MissionResult missionResult;


        public void missionResult(final MissionResult missionResult) {
            this.missionResult = missionResult;
        }

        public void assignedCrew(final List<CrewMember> assignedCrew) {
            this.assignedCrew = assignedCrew;
        }

        public void assignedSpaceShift(final Spaceship assignedSpaceShift) {
            this.assignedSpaceShift = assignedSpaceShift;
        }

        public void distance(final Long distance) {
            this.distance = distance;
        }

        public void endDate(final LocalDateTime endDate) {
            this.endDate = endDate;
        }

        public void startDate(final LocalDateTime startDate) {
            this.startDate = startDate;
        }

        @Override
        public FlightMissionCriteria build() {
            return new FlightMissionCriteria(id, name, startDate, endDate, distance,
                    assignedSpaceShift, assignedCrew, missionResult);
        }
    }
}
