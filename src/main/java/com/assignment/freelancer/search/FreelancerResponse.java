package com.assignment.freelancer.search;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FreelancerResponse {
    private Long id;
    private Long technicalAbility;
    private Long viewCount;
    private LocalDateTime createdAt;

    public FreelancerResponse(Long id, Long technicalAbility, Long viewCount, LocalDateTime createdAt) {
        this.id = id;
        this.technicalAbility = technicalAbility;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
    }
}
