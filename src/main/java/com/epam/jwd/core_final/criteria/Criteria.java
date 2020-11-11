package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {
    private Long id;
    private String name;

    protected Criteria(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static abstract class Builder {
        Long id;
        String name;

        public Builder() {
            this.configure();
        }

        protected void configure() {
        }

        public void id(final Long id) {
            this.id = id;
        }

        public void name(final String name) {
            this.name = name;
        }

        public abstract Criteria build();
    }
}
