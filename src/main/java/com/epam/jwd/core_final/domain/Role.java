package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

import java.util.Arrays;

public enum Role implements BaseEntity {
    MISSION_SPECIALIST(1L),
    FLIGHT_ENGINEER(2L),
    PILOT(3L),
    COMMANDER(4L);

    private final Long id;

    Role(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * todo via java.lang.enum methods!
     */
    @Override
    public String getName() {
        Role role = resolveRoleById(id.intValue());
        return role.toString();
    }

    /**
     * todo via java.lang.enum methods!
     *
     * @throws UnknownEntityException if such id does not exist
     */
    public static Role resolveRoleById(int id) {
        Role[] role = Role.values();
        return Arrays.stream(role)
                .filter(role1 -> role1.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UnknownEntityException("Role not found"));
    }
}
