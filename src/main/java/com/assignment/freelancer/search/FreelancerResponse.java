package com.assignment.freelancer.search;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FreelancerResponse {
    private Long id;
    private Long viewCount;
    private LocalDateTime createdAt;

    public FreelancerResponse(Long id, Long viewCount, LocalDateTime createdAt) {
        this.id = id;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
    }
}
