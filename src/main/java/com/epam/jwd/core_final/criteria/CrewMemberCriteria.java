package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;

    private CrewMemberCriteria(Long id, String name, Role role, Rank rank, Boolean isReadyForNextMissions) {
        super(id, name);
        this.role = role;
        this.rank = rank;
        this.isReadyForNextMissions = isReadyForNextMissions;
    }

    public Rank getRank() {
        return rank;
    }

    public Role getRole() {
        return role;
    }

    public Boolean getIsReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    public static class CrewBuilder extends Criteria.Builder {
        private Role role;
        private Rank rank;
        private Boolean isReadyForNextMissions;

        public void role(final Role role) {
            this.role = role;
        }

        public void rank(final Rank rank) {
            this.rank = rank;
        }

        public void isReadyForNextMissions(final Boolean isReadyForNextMissions) {
            this.isReadyForNextMissions = isReadyForNextMissions;
        }

        @Override
        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(id, name, role, rank, isReadyForNextMissions);
        }
    }
}
