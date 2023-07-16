package com.assignment.freelancer.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {

    @Query("select f from Freelancer f where f.id in (:ids)")
    List<Freelancer> findAllIdIn(@Param("ids") List<Long> freelancerIds, Pageable pageable);
}
