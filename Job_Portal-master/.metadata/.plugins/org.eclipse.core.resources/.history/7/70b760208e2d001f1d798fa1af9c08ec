package com.userservice.service;

import java.util.List;

import com.userservice.exception.RecruiterNotFoundException;
import com.userservice.model.Recruiter;

public interface RecruiterService {
    Recruiter addRecruiter(Recruiter recruiter);
    List<Recruiter> getAllRecruiters() throws RecruiterNotFoundException;
    Recruiter getRecruiterById(long id) throws RecruiterNotFoundException;
    Recruiter updateRecruiter(Recruiter recruiter) throws RecruiterNotFoundException;
    String deleteRecruiter(long id) throws RecruiterNotFoundException;
}
