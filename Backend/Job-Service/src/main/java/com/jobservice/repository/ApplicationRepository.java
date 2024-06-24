package com.jobservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobservice.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{



}
