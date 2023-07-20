package com.assignment.freelancer.search.dto;

import lombok.Getter;

@Getter
public class KeywordResponse {
    private String name;
    private int abilityLevel;

    public KeywordResponse(String name, int abilityLevel) {
        this.name = name;
        this.abilityLevel = abilityLevel;
    }
}
