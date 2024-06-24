package com.userservice.service;

import java.util.List;

import com.userservice.exception.ApplicantNotFoundException;
import com.userservice.model.Applicant;

public interface ApplicantService {
    Applicant addApplicant(Applicant applicant);
    List<Applicant> getAllApplicants() throws ApplicantNotFoundException ;
    Applicant updateApplicant(Applicant applicant) throws ApplicantNotFoundException ;
	Applicant getApplicantByEmailId(String emailId) throws ApplicantNotFoundException;
	String deleteApplicant(String emailId) throws ApplicantNotFoundException;
}
