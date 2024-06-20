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
    public Applicant getApplicantById(long id) throws ApplicantNotFoundException  {
        return applicantRepository.findById(id).orElseThrow(() -> new ApplicantNotFoundException("Applicant not found with id " + id));
    }

    @Override
    public Applicant updateApplicant(Applicant applicant) throws ApplicantNotFoundException  {
        Optional<Applicant> existingApplicant = applicantRepository.findById(applicant.getApplicantId());
        if (existingApplicant.isPresent()) {
            Applicant updatedApplicant = existingApplicant.get();
            updatedApplicant.setFirstName(applicant.getFirstName());
            updatedApplicant.setLastName(applicant.getLastName());
            updatedApplicant.setEmailId(applicant.getEmailId());
            updatedApplicant.setCity(applicant.getCity());
            updatedApplicant.setState(applicant.getState());
            updatedApplicant.setCountry(applicant.getCountry());
            updatedApplicant.setPincode(applicant.getPincode());
            updatedApplicant.setSkills(applicant.getSkills());
            updatedApplicant.setExperience(applicant.getExperience());
            updatedApplicant.setEducation(applicant.getEducation());
            return applicantRepository.save(updatedApplicant);
        } else {
            throw new ApplicantNotFoundException("Applicant not found with id " + applicant.getApplicantId());
        }
    }

    @Override
    public String deleteApplicant(long id)  throws ApplicantNotFoundException {
        if (applicantRepository.existsById(id)) {
            applicantRepository.deleteById(id);
            return "Applicant deleted";
        } else {
            throw new ApplicantNotFoundException("Applicant not found with id " + id);
        }
    }
}
