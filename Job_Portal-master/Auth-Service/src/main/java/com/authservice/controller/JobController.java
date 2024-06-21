package com.authservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.exception.ApplicantNotFoundException;
import com.authservice.exception.ApplicationNotFoundException;
import com.authservice.exception.JobNotFoundException;
import com.authservice.proxyentity.job.Application;
import com.authservice.proxyentity.job.ApplicationConstant;
import com.authservice.proxyentity.job.Job;
import com.authservice.proxyentity.job.JobConstant;
import com.authservice.proxyentity.job.JobProxyController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/JP/Job")
public class JobController {

	@Autowired
	private JobProxyController jobProxy;
	
	@PostMapping(ApplicationConstant.ADD_APPLICATION)
	public ResponseEntity<Application> addJobApplication(@RequestBody @Valid Application application)
			throws ApplicantNotFoundException{
		return jobProxy.addJobApplication(application);
	}

	@GetMapping(ApplicationConstant.GET_ALL_APPLICATIONS)
	public ResponseEntity<List<Application>> getAllJobApplications() throws ApplicationNotFoundException{
		return jobProxy.getAllJobApplications();
	}

	@GetMapping(ApplicationConstant.GET_APPLICATION_BY_ID)
	public ResponseEntity<Optional<Application>> getJobApplicationById(@PathVariable long id)
			throws ApplicationNotFoundException{
		return jobProxy.getJobApplicationById(id);
	}

	@PutMapping(ApplicationConstant.UPDATE_APPLICATION)
	public ResponseEntity<Application> updateJobApplication(@RequestBody @Valid Application application)
			throws ApplicationNotFoundException, ApplicantNotFoundException{
		return jobProxy.updateJobApplication(application);
	}

	@DeleteMapping(ApplicationConstant.DELETE_APPLICATION)
	public ResponseEntity<String> deleteJobApplication(@PathVariable long id) throws ApplicationNotFoundException{
		return jobProxy.deleteJobApplication(id);
	}
	
	 @PostMapping(JobConstant.ADD_JOB)
	    public ResponseEntity<Job> addJob(@RequestBody @Valid Job job) {
		 return jobProxy.addJob(job);
	 }

	    @GetMapping(JobConstant.GET_ALL_JOBS)
	    public ResponseEntity<List<Job>> getAllJobs() throws JobNotFoundException{
	    	return jobProxy.getAllJobs();
	    }

	    @GetMapping(JobConstant.GET_JOBS_BY_CATEGORY)
	    public ResponseEntity<List<Job>> getJobsByCategory(@PathVariable String category) throws JobNotFoundException {
	    	return jobProxy.getJobsByCategory(category);
	    }

	    @GetMapping(JobConstant.GET_JOBS_BY_TYPE)
	    public ResponseEntity<List<Job>> getJobsByType(@PathVariable String type) throws JobNotFoundException {
	    	return jobProxy.getJobsByType(type);
	    }

	    @GetMapping(JobConstant.GET_JOBS_BY_SALARY)
	    public ResponseEntity<List<Job>> getJobsBySalary(@PathVariable double salary) throws JobNotFoundException{
	    	return jobProxy.getJobsBySalary(salary);
	    }

	    @PutMapping(JobConstant.UPDATE_JOB)
	    public ResponseEntity<Job> updateJob(@RequestBody @Valid Job job) throws JobNotFoundException {
	    	return jobProxy.updateJob(job);
	    }
	    @DeleteMapping(JobConstant.DELETE_JOB)
	    public ResponseEntity<String> deleteJob(@PathVariable long jobId) throws JobNotFoundException{
	    	return jobProxy.deleteJob(jobId);
	    }
}
