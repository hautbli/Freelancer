package com.assignment.freelancer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Keyword extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long searchCount;

    public Keyword() {
    }

    public Keyword(String name, Long searchCount) {
        this.name = name;
        this.searchCount = searchCount;
    }
}
