package com.assignment.freelancer.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FreelancerKeywordRepository extends JpaRepository<FreelancerKeyword, Long> {

    @Query("select flkw.freelancer.id from FreelancerKeyword flkw where flkw.keyword.id in (:keywordIds)")
    List<Long> findAllByKeywordIdIn(@Param("keywordIds") List<Long> keywordIds);
}
