package com.jobservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jobservice.exception.ApplicantNotFoundException;
import com.jobservice.exception.ApplicationNotFoundException;
import com.jobservice.model.Application;

@Service
public interface ApplicationService {


	Application addJobApplication(Application application) throws ApplicantNotFoundException;

	List<Application> getAllJobApplications() throws ApplicationNotFoundException;

	Application updateJobApplication(Application application) throws ApplicationNotFoundException, ApplicantNotFoundException;

	String deleteJobApplication(long applicationId) throws ApplicationNotFoundException;

	Optional<Application> getJobApplicationById(long applicationId) throws ApplicationNotFoundException;

}
