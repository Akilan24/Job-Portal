package com.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.exception.ApplicantNotFoundException;
import com.userservice.model.Applicant;
import com.userservice.repository.ApplicantRepository;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Override
    public Applicant addApplicant(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @Override
    public List<Applicant> getAllApplicants() throws ApplicantNotFoundException {
    	List<Applicant> findAll = applicantRepository.findAll();
		if (findAll.isEmpty()) {
			throw new ApplicantNotFoundException("Recruiter not found");
		}

        return applicantRepository.findAll();
    }

    @Override
    public Applicant getApplicantByEmailId(String emailId) throws ApplicantNotFoundException  {
        return applicantRepository.findById(emailId).orElseThrow(() -> new ApplicantNotFoundException("Applicant not found with id " + emailId));
    }

    @Override
    public Applicant updateApplicant(Applicant applicant) throws ApplicantNotFoundException  {
        Optional<Applicant> existingApplicant = applicantRepository.findById(applicant.getEmailId());
        if (existingApplicant.isPresent()) {
            Applicant updatedApplicant = existingApplicant.get();
            updatedApplicant.setFirstName(applicant.getFirstName());
            updatedApplicant.setLastName(applicant.getLastName());
            updatedApplicant.setCity(applicant.getCity());
            updatedApplicant.setState(applicant.getState());
            updatedApplicant.setCountry(applicant.getCountry());
            updatedApplicant.setPincode(applicant.getPincode());
            updatedApplicant.setSkills(applicant.getSkills());
            updatedApplicant.setExperience(applicant.getExperience());
            updatedApplicant.setEducation(applicant.getEducation());
            return applicantRepository.save(updatedApplicant);
        } else {
            throw new ApplicantNotFoundException("Applicant not found with id " + applicant.getEmailId());
        }
    }

    @Override
    public String deleteApplicant(String emailId)  throws ApplicantNotFoundException {
        if (applicantRepository.existsById(emailId)) {
            applicantRepository.deleteById(emailId);
            return "Applicant deleted";
        } else {
            throw new ApplicantNotFoundException("Applicant not found with id " + emailId);
        }
    }
}
