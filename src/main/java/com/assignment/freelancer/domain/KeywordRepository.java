package com.assignment.freelancer.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    @Query("select k.id from Keyword k where k.name like :name%")
    List<Long> findAllLike(@Param("name") String name);
}
