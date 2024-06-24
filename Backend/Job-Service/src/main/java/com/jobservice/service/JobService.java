package com.jobservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobservice.exception.JobNotFoundException;
import com.jobservice.model.Job;

@Service
public interface JobService {
	
	Job addJob(Job job);
	
	List<Job> getAllJob() throws JobNotFoundException;
	
	String deleteJob(long jobId) throws JobNotFoundException;
	
	Job updateJob(Job job) throws JobNotFoundException;
	
	List<Job> getByJobCategory(String jobCategory) throws JobNotFoundException;
	
	List<Job> getByJobType(String jobType) throws JobNotFoundException;
	
	List<Job> getBySalary(double salary) throws JobNotFoundException;
	
	
	
	
	
	
	
	

}
