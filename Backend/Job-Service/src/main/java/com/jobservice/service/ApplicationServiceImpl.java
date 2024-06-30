package com.jobservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobservice.exception.ApplicationNotFoundException;
import com.jobservice.model.Application;
import com.jobservice.model.Job;
import com.jobservice.repository.ApplicationRepository;
import com.jobservice.repository.JobRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationRepository repo;

	@Autowired
	private JobRepository jrepo;

	@Override
	public List<Application> getAllJobApplications(String emailId) throws ApplicationNotFoundException {
		List<Application> findAll = repo.findAll().stream()
				.filter(f -> f.getApplicantEmailId().equalsIgnoreCase(emailId)).collect(Collectors.toList());
		if (findAll.isEmpty()) {
			throw new ApplicationNotFoundException("Job applications not found");
		}
		return findAll;
	}

	@Override
	public Application getJobApplicationById(long applicationId) throws ApplicationNotFoundException {
		Optional<Application> application = repo.findById(applicationId);
		if (!application.isPresent()) {
			throw new ApplicationNotFoundException("Job application not found with the id :" + applicationId);
		}
		return application.get();
	}

	@Override
	public Application updateJobApplication(Application application)
			throws ApplicationNotFoundException {
		Optional<Application> existingJobApplication = repo.findById(application.getApplicationId());
		if (existingJobApplication.isPresent()) {
			Application job = existingJobApplication.get();
			job.setAppliedDate(application.getAppliedDate());
			job.setStatus(application.getStatus());
			job.setApplicantEmailId(application.getApplicantEmailId());
			return repo.save(job);
		} else {
			throw new ApplicationNotFoundException(
					"Job application not found with the id :" + application.getApplicationId());
		}
	}

	@Override
	public String deleteJobApplication(long applicationId) throws ApplicationNotFoundException {
		if (repo.existsById(applicationId)) {
			repo.deleteById(applicationId);
			return "Job application deleted with id :" + applicationId;
		}
		throw new ApplicationNotFoundException("Job application not found with the id :" + applicationId);
	}

	@Override
	public Application addJobApplication(long jobId, String emailId) {
		Job job = jrepo.findById(jobId).get();
		Application a = new Application();
		a.setJobTitle(job.getJobTitle());
		a.setCompany(job.getCompany());
		a.setStatus("Submitted");
		a.setAppliedDate(LocalDate.now());
		a.setApplicantEmailId(emailId);
		job.getApplications().add(a);
		jrepo.save(job);
		return a;
	}
}
