package com.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.authservice.exception.PostNotFoundException;
import com.authservice.proxyentity.job.Application;
import com.authservice.proxyentity.job.ApplicationConstant;
import com.authservice.proxyentity.job.Job;
import com.authservice.proxyentity.job.JobConstant;
import com.authservice.proxyentity.job.JobProxyController;
import com.authservice.proxyentity.job.Post;
import com.authservice.proxyentity.job.PostConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/JP/Job")
public class JobController {

	@Autowired
	private JobProxyController jobProxy;

	@PreAuthorize("hasAuthority('applicant')")
	@PostMapping(ApplicationConstant.ADD_APPLICATION)
	public ResponseEntity<Application> addJobApplication(@RequestBody @Valid Application application)
			throws ApplicantNotFoundException {
		return jobProxy.addJobApplication(application);
	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@GetMapping(ApplicationConstant.GET_ALL_APPLICATIONS)
	public ResponseEntity<List<Application>> getAllJobApplications() throws ApplicationNotFoundException {
		return jobProxy.getAllJobApplications();
	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@GetMapping(ApplicationConstant.GET_APPLICATION_BY_ID)
	public ResponseEntity<Application> getJobApplicationById(@PathVariable long id)
			throws ApplicationNotFoundException {
		return jobProxy.getJobApplicationById(id);
	}

	@PreAuthorize("hasAuthority('applicant')")
	@PutMapping(ApplicationConstant.UPDATE_APPLICATION)
	public ResponseEntity<Application> updateJobApplication(@RequestBody @Valid Application application)
			throws ApplicationNotFoundException, ApplicantNotFoundException {
		return jobProxy.updateJobApplication(application);
	}

	@PreAuthorize("hasAuthority('applicant')")
	@DeleteMapping(ApplicationConstant.DELETE_APPLICATION)
	public ResponseEntity<String> deleteJobApplication(@PathVariable long id) throws ApplicationNotFoundException {
		return jobProxy.deleteJobApplication(id);
	}

	@PreAuthorize("hasAuthority('recruiter')")
	@PostMapping(JobConstant.ADD_JOB)
	public ResponseEntity<Job> addJob(@RequestBody @Valid Job job) {
		return jobProxy.addJob(job);
	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@GetMapping(JobConstant.GET_ALL_JOBS)
	public ResponseEntity<List<Job>> getAllJobs() throws JobNotFoundException {
		return jobProxy.getAllJobs();
	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@GetMapping(JobConstant.GET_JOBS_BY_CATEGORY)
	public ResponseEntity<List<Job>> getJobsByCategory(@PathVariable String category) throws JobNotFoundException {
		return jobProxy.getJobsByCategory(category);
	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@GetMapping(JobConstant.GET_JOBS_BY_TYPE)
	public ResponseEntity<List<Job>> getJobsByType(@PathVariable String type) throws JobNotFoundException {
		return jobProxy.getJobsByType(type);
	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@GetMapping(JobConstant.GET_JOBS_BY_SEARCH)
	public ResponseEntity<List<Job>> getJobsBySearch(@PathVariable String search) throws JobNotFoundException {
		return jobProxy.getJobsBySearch(search);
	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@GetMapping(JobConstant.GET_JOBS_BY_ID)
	public ResponseEntity<Job> getJobsById(@PathVariable long jobId) throws JobNotFoundException {
		return jobProxy.getJobsById(jobId);
	}

	@PreAuthorize("hasAuthority('recruiter')")
	@PutMapping(JobConstant.UPDATE_JOB)
	public ResponseEntity<Job> updateJob(@RequestBody @Valid Job job) throws JobNotFoundException {
		return jobProxy.updateJob(job);
	}

	@PreAuthorize("hasAuthority('recruiter')")
	@DeleteMapping(JobConstant.DELETE_JOB)
	public ResponseEntity<String> deleteJob(@PathVariable long jobId) throws JobNotFoundException {
		return jobProxy.deleteJob(jobId);
	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@PostMapping(PostConstants.ADD_POST)
	public ResponseEntity<Post> addPost(@RequestBody @Valid Post post) {
		return jobProxy.addPost(post);
	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@GetMapping(PostConstants.GET_ALL_POSTS)
	public ResponseEntity<List<Post>> getAllPosts() throws PostNotFoundException {
		return jobProxy.getAllPosts();

	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@GetMapping(PostConstants.GET_POST_BY_ID)
	public ResponseEntity<Post> getPostById(@PathVariable int id) throws PostNotFoundException {
		return jobProxy.getPostById(id);

	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@PutMapping(PostConstants.UPDATE_POST)
	public ResponseEntity<Post> updatePost(@RequestBody @Valid Post post) throws PostNotFoundException {
		return jobProxy.updatePost(post);

	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@DeleteMapping(PostConstants.DELETE_POST)
	public ResponseEntity<String> deletePost(@PathVariable int id) throws PostNotFoundException {
		return jobProxy.deletePost(id);

	}
}
