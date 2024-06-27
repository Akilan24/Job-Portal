package com.userservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.exception.ApplicantNotFoundException;
import com.userservice.model.Applicant;
import com.userservice.model.Education;
import com.userservice.model.WorkExperience;
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
	public Applicant getApplicantByEmailId(String emailId) throws ApplicantNotFoundException {
		return applicantRepository.findById(emailId)
				.orElseThrow(() -> new ApplicantNotFoundException("Applicant not found with id " + emailId));
	}

	@Override
	public Applicant updateApplicant(Applicant applicant) throws ApplicantNotFoundException {
		Optional<Applicant> existingApplicant = applicantRepository.findById(applicant.getEmailId());
		if (existingApplicant.isPresent()) {
			Applicant updatedApplicant = existingApplicant.get();
			updatedApplicant.setName(applicant.getName());
			updatedApplicant.setCity(applicant.getCity());
			updatedApplicant.setState(applicant.getState());
			updatedApplicant.setCountry(applicant.getCountry());
			updatedApplicant.setPincode(applicant.getPincode());
			updatedApplicant.setSkills(applicant.getSkills());
			updatedApplicant.setExperience(applicant.getExperience());
			updatedApplicant.setEducation(applicant.getEducation());
			updatedApplicant.setHeadline(applicant.getHeadline());
			return applicantRepository.save(updatedApplicant);
		} else {
			throw new ApplicantNotFoundException("Applicant not found with id " + applicant.getEmailId());
		}
	}

	@Override
	public String deleteApplicant(String emailId) throws ApplicantNotFoundException {
		if (applicantRepository.existsById(emailId)) {
			applicantRepository.deleteById(emailId);
			return "Applicant deleted";
		} else {
			throw new ApplicantNotFoundException("Applicant not found with id " + emailId);
		}
	}

	@Override
	public List<Education> getAllEducation(String emailId) {
		Optional<Applicant> existingApplicant = applicantRepository.findByEmailId(emailId);
		return existingApplicant.get().getEducation();

	}

	@Override
	public List<Education> addEducation(String emailId, Education education) {
		Optional<Applicant> existingApplicant = applicantRepository.findByEmailId(emailId);
		List<Education> edu = existingApplicant.get().getEducation();
		edu.add(education);
		existingApplicant.get().setEducation(edu);
		applicantRepository.save(existingApplicant.get());
		return edu;
	}

	@Override
	public Education getEducation(String emailId, String degree) {
		Optional<Applicant> existingApplicant = applicantRepository.findByEmailId(emailId);
		return existingApplicant.get().getEducation().stream().filter(e -> e.getDegree().equalsIgnoreCase(degree))
				.collect(Collectors.toList()).get(0);

	}

	@Override
	public List<Education> updateEducation(String emailId, Education education) {
		Optional<Applicant> existingApplicant = applicantRepository.findByEmailId(emailId);
		List<Education> collect = existingApplicant.get().getEducation().stream()
				.filter(e -> !e.getDegree().equalsIgnoreCase(education.getDegree())).collect(Collectors.toList());
		collect.add(education);
		existingApplicant.get().setEducation(collect);
		applicantRepository.save(existingApplicant.get());
		return collect;
	}

	@Override
	public String deleteEducation(String emailId, String degree) {
		Optional<Applicant> existingApplicant = applicantRepository.findByEmailId(emailId);
		List<Education> collect = existingApplicant.get().getEducation().stream()
				.filter(e -> !e.getDegree().equalsIgnoreCase(degree)).collect(Collectors.toList());
		existingApplicant.get().setEducation(collect);
		applicantRepository.save(existingApplicant.get());
		return "Education deleted";
	}

	@Override
	public List<WorkExperience> getAllWorkExperience(String emailId) {
		Optional<Applicant> existingApplicant = applicantRepository.findByEmailId(emailId);
		return existingApplicant.get().getExperience();
	}

	@Override
	public List<WorkExperience> addWorkExperience(String emailId, WorkExperience experience) {
		Optional<Applicant> existingApplicant = applicantRepository.findByEmailId(emailId);
		List<WorkExperience> exp = existingApplicant.get().getExperience();
		exp.add(experience);
		existingApplicant.get().setExperience(exp);
		applicantRepository.save(existingApplicant.get());
		return exp;
	}

	@Override
	public WorkExperience getWorkExperience(String emailId, String company) {
		Optional<Applicant> existingApplicant = applicantRepository.findByEmailId(emailId);
		return existingApplicant.get().getExperience().stream().filter(e -> e.getCompany().equalsIgnoreCase(company))
				.collect(Collectors.toList()).get(0);
	}

	@Override
	public List<WorkExperience> updateWorkExperience(String emailId, WorkExperience experience) {
		Optional<Applicant> existingApplicant = applicantRepository.findByEmailId(emailId);
		List<WorkExperience> collect = existingApplicant.get().getExperience().stream()
				.filter(e -> !e.getCompany().equalsIgnoreCase(experience.getCompany())).collect(Collectors.toList());
		collect.add(experience);
		existingApplicant.get().setExperience(collect);
		applicantRepository.save(existingApplicant.get());
		return collect;
	}

	@Override
	public String deleteWorkExperience(String emailId, String company) {
		Optional<Applicant> existingApplicant = applicantRepository.findByEmailId(emailId);
		List<WorkExperience> collect = existingApplicant.get().getExperience().stream()
				.filter(e -> !e.getCompany().equalsIgnoreCase(company)).collect(Collectors.toList());
		existingApplicant.get().setExperience(collect);
		applicantRepository.save(existingApplicant.get());
		return "Experience deleted";
	}
}
