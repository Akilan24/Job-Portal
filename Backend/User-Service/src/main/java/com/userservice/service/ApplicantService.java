package com.userservice.service;

import java.util.List;

import com.userservice.exception.ApplicantNotFoundException;
import com.userservice.model.Applicant;
import com.userservice.model.Education;
import com.userservice.model.WorkExperience;

public interface ApplicantService {
    Applicant addApplicant(Applicant applicant);
    List<Applicant> getAllApplicants() throws ApplicantNotFoundException ;
    Applicant updateApplicant(Applicant applicant) throws ApplicantNotFoundException ;
	Applicant getApplicantByEmailId(String emailId) throws ApplicantNotFoundException;
	String deleteApplicant(String emailId) throws ApplicantNotFoundException;
	
	List<Education> getAllEducation(String emailId);
	List<Education> addEducation(String emailId, Education education);
	Education getEducation(String emailId,String degree);
	List<Education> updateEducation(String emailId, Education education);
	String deleteEducation(String emailId,String degree);
	
	List<WorkExperience> getAllWorkExperience(String emailId);
	List<WorkExperience> addWorkExperience(String emailId, WorkExperience experience);
	WorkExperience getWorkExperience(String emailId,String company);
	List<WorkExperience> updateWorkExperience(String emailId, WorkExperience experience);
	String deleteWorkExperience(String emailId,String company);
}
