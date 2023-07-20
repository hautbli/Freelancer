package com.assignment.freelancer.search;

import lombok.Getter;

@Getter
public class KeywordResponse {
    private Long id;
    private String name;
    private Long searchCount;

    public KeywordResponse(Long id, String name, Long searchCount) {
        this.id = id;
        this.name = name;
        this.searchCount = searchCount;
    }
}
