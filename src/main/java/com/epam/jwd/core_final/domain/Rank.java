package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

import java.util.Arrays;

public enum Rank implements BaseEntity {
    TRAINEE(1L),
    SECOND_OFFICER(2L),
    FIRST_OFFICER(3L),
    CAPTAIN(4L);

    private final Long id;

    Rank(Long id) {
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
        Rank rank = resolveRankById(id.intValue());
        return rank.toString();
    }

    /**
     * todo via java.lang.enum methods!
     *
     * @throws UnknownEntityException if such id does not exist
     */
    public static Rank resolveRankById(int id) {
        Rank[] rank = Rank.values();
        return Arrays.stream(rank)
                .filter(rank1 -> rank1.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UnknownEntityException("Role not found"));
    }
}
