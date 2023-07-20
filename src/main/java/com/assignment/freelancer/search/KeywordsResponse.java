package com.assignment.freelancer.search;

import com.assignment.freelancer.domain.Keyword;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class KeywordsResponse {
    private List<KeywordResponse> keywordResponses;

    public KeywordsResponse(List<KeywordResponse> keywordResponses) {
        this.keywordResponses = keywordResponses;
    }

    public static KeywordsResponse of(List<Keyword> keywords) {
        return new KeywordsResponse(
                keywords.stream()
                        .map(keyword ->
                                new KeywordResponse(keyword.getId(), keyword.getName(), keyword.getSearchCount()))
                        .collect(Collectors.toList())
        );
    }
}
