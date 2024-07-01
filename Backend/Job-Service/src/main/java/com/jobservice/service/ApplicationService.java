package com.jobservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobservice.exception.ApplicantNotFoundException;
import com.jobservice.exception.ApplicationNotFoundException;
import com.jobservice.model.Application;

@Service
public interface ApplicationService {


	Application addJobApplication(long jobId,String emailId) throws ApplicantNotFoundException;

	List<Application> getAllJobApplications(String emailId) throws ApplicationNotFoundException;

	Application updateJobApplication(Application application) throws ApplicationNotFoundException;

	String deleteJobApplication(long applicationId) throws ApplicationNotFoundException;

	Application getJobApplicationById(long applicationId) throws ApplicationNotFoundException;

}
