package com.assignment.freelancer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Freelancer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long viewCount;

    @OneToMany(mappedBy = "freelancer")
    private List<FreelancerKeyword> freelancerKeywords;

    public Freelancer() {
    }

    public Freelancer(Long id, Long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }
}
