package com.jobservice.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.jobservice.exception.ApplicationNotFoundException;
import com.jobservice.exception.JobNotFoundException;
import com.jobservice.model.Application;
import com.jobservice.model.Job;

@Service
public interface JobService {

	Job addJob(String emailId, Job job);

	List<Job> getAllJob() throws JobNotFoundException;

	String deleteJob(long jobId) throws JobNotFoundException;

	Job updateJob(Job job) throws JobNotFoundException;

	List<Job> getByJobCategory(String jobCategory) throws JobNotFoundException;

	List<Job> getByEmail(String emailId) throws JobNotFoundException;

	List<Job> getByJobType(String jobType) throws JobNotFoundException;

	List<Job> getBySearch(String search) throws JobNotFoundException;

	Set<Application> getApplicationByJobId(long jobId) throws JobNotFoundException, ApplicationNotFoundException;

	Job getByJobId(long jobId) throws JobNotFoundException;

}
