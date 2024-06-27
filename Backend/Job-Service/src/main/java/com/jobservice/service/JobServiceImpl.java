package com.jobservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobservice.exception.JobNotFoundException;
import com.jobservice.model.Job;
import com.jobservice.repository.JobRepository;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepo;

	@Override
	public List<Job> getAllJob() throws JobNotFoundException {
		List<Job> findAll = jobRepo.findAll();
		if (findAll.isEmpty()) {
			throw new JobNotFoundException("Job not found");
		}
		return findAll;
	}

	@Override
	public String deleteJob(long jobId) throws JobNotFoundException {
		if (jobRepo.existsById(jobId)) {
			jobRepo.deleteById(jobId);
			return "Job deleted with id :" + jobId;
		}
		throw new JobNotFoundException("Job not found");
	}

	@Override
	public Job updateJob(Job job) throws JobNotFoundException {
		Optional<Job> existing = jobRepo.findById(job.getJobId());
		if (existing.isPresent()) {
			Job newJob = existing.get();
			newJob.setJobTitle(job.getJobTitle());
			newJob.setJobDescription(job.getJobDescription());
			newJob.setCompany(job.getCompany());
			newJob.setSalary(job.getSalary());
			newJob.setExperience(job.getExperience());
			newJob.setJobType(job.getJobType());
			newJob.setJobCategory(job.getJobCategory());
			newJob.setCity(job.getCity());
			newJob.setState(job.getState());
			newJob.setCountry(job.getCountry());
			newJob.setPincode(job.getPincode());
			newJob.setPostedDate(job.getPostedDate());
			newJob.setLogo(job.getLogo());
			return jobRepo.save(newJob);
		}
		throw new JobNotFoundException("Job not found");
	}

	@Override
	public List<Job> getByJobCategory(String jobCategory) throws JobNotFoundException {
		List<Job> byJobCategory = jobRepo.findByJobCategory(jobCategory);
		if (byJobCategory.isEmpty()) {
			throw new JobNotFoundException("Job not found for the job category :" + jobCategory);
		}
		return byJobCategory;
	}

	@Override
	public List<Job> getByJobType(String jobType) throws JobNotFoundException {
		List<Job> byJobType = jobRepo.findByJobType(jobType);
		if (byJobType.isEmpty()) {
			throw new JobNotFoundException("Job not found for the job type :" + jobType);
		}
		return byJobType;
	}

	@Override
	public Job getByJobId(long jobId) throws JobNotFoundException {
		Optional<Job> byId = jobRepo.findById(jobId);
		if (byId.isEmpty()) {
			throw new JobNotFoundException("Job not found for the salary :" + jobId);
		}
		return byId.get();
	}

	@Override
	public Job addJob(Job job) {
		return jobRepo.save(job);
	}

}
