package com.assignment.freelancer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long>, CustomFreelancerRepository {
}
