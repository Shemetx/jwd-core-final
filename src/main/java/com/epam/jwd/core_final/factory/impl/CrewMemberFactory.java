package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {
    private static CrewMemberFactory instance = null;

    private CrewMemberFactory() {

    }

    public static CrewMemberFactory getInstance() {
        if (instance == null) {
            instance = new CrewMemberFactory();
        }
        return instance;
    }

    @Override
    public CrewMember create(Object... args) {
        return new CrewMember((long) args[0], (String) args[1], (Role) args[2], (Rank) args[3]);
    }
}
