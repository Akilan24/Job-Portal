package com.authservice.proxyentity.job;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.exception.ApplicantNotFoundException;
import com.authservice.exception.ApplicationNotFoundException;
import com.authservice.exception.JobNotFoundException;

import jakarta.validation.Valid;

@FeignClient(name = "JOB-SERVICE", url = "http://localhost:8082/Job")
public interface JobProxyController {

	@PostMapping(ApplicationConstant.ADD_APPLICATION)
	public ResponseEntity<Application> addJobApplication(@RequestBody @Valid Application application)
			throws ApplicantNotFoundException;

	@GetMapping(ApplicationConstant.GET_ALL_APPLICATIONS)
	public ResponseEntity<List<Application>> getAllJobApplications() throws ApplicationNotFoundException;

	  @GetMapping(ApplicationConstant.GET_APPLICATION_BY_ID)
	    public ResponseEntity<Optional<Application>> getJobApplicationById(@PathVariable long id)
	            throws ApplicationNotFoundException;

	@PutMapping(ApplicationConstant.UPDATE_APPLICATION)
	public ResponseEntity<Application> updateJobApplication(@RequestBody @Valid Application application)
			throws ApplicationNotFoundException, ApplicantNotFoundException;

	@DeleteMapping(ApplicationConstant.DELETE_APPLICATION)
	public ResponseEntity<String> deleteJobApplication(@PathVariable long id) throws ApplicationNotFoundException;

	@PostMapping(JobConstant.ADD_JOB)
	public ResponseEntity<Job> addJob(@RequestBody @Valid Job job);

	@GetMapping(JobConstant.GET_ALL_JOBS)
	public ResponseEntity<List<Job>> getAllJobs() throws JobNotFoundException;

	@GetMapping(JobConstant.GET_JOBS_BY_CATEGORY)
	public ResponseEntity<List<Job>> getJobsByCategory(@PathVariable String category) throws JobNotFoundException;

	@GetMapping(JobConstant.GET_JOBS_BY_TYPE)
	public ResponseEntity<List<Job>> getJobsByType(@PathVariable String type) throws JobNotFoundException;

	@GetMapping(JobConstant.GET_JOBS_BY_SALARY)
	public ResponseEntity<List<Job>> getJobsBySalary(@PathVariable double salary) throws JobNotFoundException;

	@PutMapping(JobConstant.UPDATE_JOB)
	public ResponseEntity<Job> updateJob(@RequestBody @Valid Job job) throws JobNotFoundException;

	@DeleteMapping(JobConstant.DELETE_JOB)
	public ResponseEntity<String> deleteJob(@PathVariable long jobId) throws JobNotFoundException;
}
