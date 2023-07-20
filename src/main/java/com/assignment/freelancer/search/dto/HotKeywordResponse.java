package com.assignment.freelancer.search.dto;

import lombok.Getter;

@Getter
public class HotKeywordResponse {
    private Long id;
    private String name;
    private Long searchCount;

    public HotKeywordResponse(Long id, String name, Long searchCount) {
        this.id = id;
        this.name = name;
        this.searchCount = searchCount;
    }
}
