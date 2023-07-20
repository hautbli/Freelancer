package com.assignment.freelancer.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class FreelancersResponse {
    @JsonProperty(value = "freelancers")
    private List<FreelancerResponse> freelancersResponses;

    public FreelancersResponse(List<FreelancerResponse> freelancersResponses) {
        this.freelancersResponses = freelancersResponses;
    }

    public static FreelancersResponse of(List<FreelancerResponse> freelancersResponses) {
        return new FreelancersResponse(freelancersResponses);
    }
}
