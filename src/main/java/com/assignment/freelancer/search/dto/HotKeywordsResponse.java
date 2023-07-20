package com.assignment.freelancer.search.dto;

import com.assignment.freelancer.domain.Keyword;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HotKeywordsResponse {
    private List<HotKeywordResponse> keywordsResponses;

    public HotKeywordsResponse(List<HotKeywordResponse> keywordsResponses) {
        this.keywordsResponses = keywordsResponses;
    }

    public static HotKeywordsResponse of(List<Keyword> keywords) {
        return new HotKeywordsResponse(
                keywords.stream()
                        .map(keyword ->
                                new HotKeywordResponse(keyword.getId(), keyword.getName(), keyword.getSearchCount()))
                        .collect(Collectors.toList())
        );
    }
}
