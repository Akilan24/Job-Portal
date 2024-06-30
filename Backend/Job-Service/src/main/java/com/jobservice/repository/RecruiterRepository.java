package com.jobservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobservice.model.Recruiter;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, String> {
}
