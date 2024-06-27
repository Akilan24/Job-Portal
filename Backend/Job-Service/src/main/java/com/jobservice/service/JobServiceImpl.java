package com.jobservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
			newJob.setRequiredSkills(job.getRequiredSkills());
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

	@Override
	public List<Job> getBySearch(String search) throws JobNotFoundException {
		List<Job> jobList=new ArrayList<Job>();
		List<Job> byJobCategory = jobRepo.findByJobCategory(search);
		if(!byJobCategory.isEmpty())
			jobList.addAll(byJobCategory);
		List<Job> byJobType = jobRepo.findByJobType(search);
		if(!byJobType.isEmpty())
			jobList.addAll(byJobType);
		List<Job> byJobSkills = jobRepo.findAll().stream().filter(f->f.getRequiredSkills().contains(search)).collect(Collectors.toList());
		if(!byJobSkills.isEmpty())
			jobList.addAll(byJobSkills);
		List<Job> byJobTitle = jobRepo.findByJobTitle(search);
		if(!byJobTitle.isEmpty())
			jobList.addAll(byJobTitle);
		return jobList;
	}

}
