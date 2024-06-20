package com.userservice.service;

import java.util.List;

import com.userservice.exception.ApplicantNotFoundException;
import com.userservice.model.Applicant;

public interface ApplicantService {
    Applicant addApplicant(Applicant applicant);
    List<Applicant> getAllApplicants() throws ApplicantNotFoundException ;
    Applicant getApplicantById(long id) throws ApplicantNotFoundException ;
    Applicant updateApplicant(Applicant applicant) throws ApplicantNotFoundException ;
    String deleteApplicant(long id) throws ApplicantNotFoundException ;
}
