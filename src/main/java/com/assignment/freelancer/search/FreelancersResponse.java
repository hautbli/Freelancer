package com.assignment.freelancer.search;

import com.assignment.freelancer.domain.Freelancer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FreelancersResponse {
    @JsonProperty(value = "freelancers")
    private List<FreelancerResponse> freelancerResponses;

    public FreelancersResponse(List<FreelancerResponse> freelancerResponses) {
        this.freelancerResponses = freelancerResponses;
    }

    public static FreelancersResponse of(List<Freelancer> freelancers) {
        List<FreelancerResponse> freelancersResponse = freelancers.stream()
                .map(freelancer ->
                        new FreelancerResponse(
                                freelancer.getId(),
                                freelancer.getViewCount(),
                                freelancer.getCreatedAt()))
                .collect(Collectors.toList());

        return new FreelancersResponse(freelancersResponse);
    }
}
