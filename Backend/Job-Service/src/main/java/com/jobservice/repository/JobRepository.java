package com.jobservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobservice.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>{

	List<Job> findByJobCategory(String jobCategory);

	List<Job> findByCompany(String company);  
	
	List<Job> findByJobType(String jobType);

	List<Job> findByJobTitle(String jobType);
	
	List<Job> findBySalary(double salary);

}
