package com.assignment.freelancer.search.dto;

import com.assignment.freelancer.domain.Freelancer;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class FreelancerResponse {
    private Long id;
    private Long viewCount;
    private LocalDateTime createdAt;
    private List<KeywordResponse> keywords;

    public FreelancerResponse(Long id, Long viewCount, LocalDateTime createdAt, List<KeywordResponse> keywords) {
        this.id = id;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.keywords = keywords;
    }

    public static FreelancerResponse of(Freelancer freelancer, List<KeywordResponse> keywords) {
        return new FreelancerResponse(freelancer.getId(), freelancer.getViewCount(), freelancer.getCreatedAt(), keywords);
    }
}
