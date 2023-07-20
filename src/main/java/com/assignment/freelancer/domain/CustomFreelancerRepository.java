package com.assignment.freelancer.domain;

import com.assignment.freelancer.search.FreelancerSortType;

import java.util.List;

public interface CustomFreelancerRepository {
    List<Freelancer> findFreelancers(List<Long> keywordIds, FreelancerSortType sort, int page, int size);
}
