package com.userservice.service;

import java.util.List;

import com.userservice.exception.RecruiterNotFoundException;
import com.userservice.model.Recruiter;

public interface RecruiterService {
    Recruiter addRecruiter(Recruiter recruiter);
    List<Recruiter> getAllRecruiters() throws RecruiterNotFoundException;
    Recruiter updateRecruiter(Recruiter recruiter) throws RecruiterNotFoundException;
	String deleteRecruiter(String emailId) throws RecruiterNotFoundException;
	Recruiter getRecruiterByEmailId(String emailId) throws RecruiterNotFoundException;

}
