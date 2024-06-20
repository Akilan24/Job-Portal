package com.jobservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jobservice.controller.ApplicantProxy;
import com.jobservice.exception.ApplicantNotFoundException;
import com.jobservice.exception.ApplicationNotFoundException;
import com.jobservice.model.Applicant;
import com.jobservice.model.Application;
import com.jobservice.repository.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationRepository repo;

	@Autowired
	private ApplicantProxy aproxy;

	@Override
	public Application addJobApplication(Application application) throws ApplicantNotFoundException {
		verifyUserExists(application.getUserId());
		return repo.save(application);
	}

	@Override
	public List<Application> getAllJobApplications() throws ApplicationNotFoundException {
		List<Application> findAll = repo.findAll();
		if (findAll.isEmpty()) {
			throw new ApplicationNotFoundException("Job applications not found");
		}
		return findAll;
	}

	@Override
	public Optional<Application> getJobApplicationById(long applicationId) throws ApplicationNotFoundException {
		Optional<Application> application = repo.findById(applicationId);
		if (!application.isPresent()) {
			throw new ApplicationNotFoundException("Job application not found with the id :" + applicationId);
		}
		return application;
	}

	@Override
	public Application updateJobApplication(Application application)
			throws ApplicationNotFoundException, ApplicantNotFoundException {
		verifyUserExists(application.getUserId());
		Optional<Application> existingJobApplication = repo.findById(application.getApplicationId());
		if (existingJobApplication.isPresent()) {
			Application job = existingJobApplication.get();
			job.setAppliedDate(application.getAppliedDate());
			job.setStatus(application.getStatus());
			job.setUserId(application.getUserId());
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

	private void verifyUserExists(long userId) throws ApplicantNotFoundException {
		ResponseEntity<Applicant> response = aproxy.getApplicantById(userId);
		if (response.getBody() == null) {
			throw new ApplicantNotFoundException("User not found with id: " + userId);
		}

	}
}
