package com.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authservice.proxyentity.user.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
	public Optional<Applicant> findByUsername(String username);
}
