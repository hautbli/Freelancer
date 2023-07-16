package com.assignment.freelancer.search;

import lombok.Getter;

public enum FreelancerSortType {
    TECHNICAL_ABILITY("technical_ability"),
    VIEW_COUNT("viewCount"),
    LATEST_AT("createdAt");

    @Getter
    private final String sortType;

    FreelancerSortType(String sortType) {
        this.sortType = sortType;
    }
}
