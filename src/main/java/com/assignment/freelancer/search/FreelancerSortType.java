package com.assignment.freelancer.search;

import lombok.Getter;

public enum FreelancerSortType {
    ABILITY_LEVEL("abilityLevel"),
    VIEW_COUNT("viewCount"),
    LATEST("createdAt");

    @Getter
    private final String sortType;

    FreelancerSortType(String sortType) {
        this.sortType = sortType;
    }
}
