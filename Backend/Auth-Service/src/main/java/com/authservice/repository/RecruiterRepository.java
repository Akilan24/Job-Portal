package com.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authservice.proxyentity.user.Recruiter;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, String> {

	public Optional<Recruiter> findByEmailId(String emailId);
}
