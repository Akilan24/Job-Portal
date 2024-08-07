package com.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.model.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {


}
