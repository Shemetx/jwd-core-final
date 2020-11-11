package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {
    // todo
    private Role role;
    private Rank rank;
    private Boolean isReadyForNextMissions;

    public CrewMember() {
    }

    public CrewMember(Long id, String name, Role role, Rank rank) {
        super(id, name);
        this.role = role;
        this.rank = rank;
        isReadyForNextMissions = true;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setReadyForNextMissions(Boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public Boolean getReadyForNextMissions() {
        return isReadyForNextMissions;
    }

    @Override
    public String toString() {
        return "CrewMember " +
                "ID = " + getId() +
                ", NAME = " + getName() +
                ", ROLE = " + role +
                ", RANK = " + rank +
                ", READY = " + isReadyForNextMissions;
    }
}
