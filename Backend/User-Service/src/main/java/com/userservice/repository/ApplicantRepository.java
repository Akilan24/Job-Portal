package com.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.model.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, String> {

	Optional<Applicant> findByEmailId(String emailId);
	
}
