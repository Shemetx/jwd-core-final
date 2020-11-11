package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private Map<Role, Short> crew;
    private Long flightDistance;
    private Boolean isReadyForNextMissions;

    private SpaceshipCriteria(Long id, String name, Map<Role, Short> crew,
                              Long flightDistance, Boolean isReadyForNextMissions) {
        super(id, name);
        this.crew = crew;
        this.flightDistance = flightDistance;
        this.isReadyForNextMissions = isReadyForNextMissions;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public Long getFlightDistance() {
        return flightDistance;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static class SpaceshipBuilder extends Criteria.Builder {
        private Map<Role, Short> crew;
        private Long flightDistance;
        private Boolean isReadyForNextMissions;

        public void crew(final Map<Role, Short> crew) {
            this.crew = crew;
        }

        public void flightDistance(final Long flightDistance) {
            this.flightDistance = flightDistance;
        }

        public void isReadyForNextMissions(final Boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
        }

        @Override
        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(id, name, crew, flightDistance, isReadyForNextMissions);
        }
    }

}
