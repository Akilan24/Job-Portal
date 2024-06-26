package com.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.constant.ApplicantConstants;
import com.userservice.exception.ApplicantNotFoundException;
import com.userservice.model.Applicant;
import com.userservice.model.Education;
import com.userservice.model.WorkExperience;
import com.userservice.service.ApplicantService;

@RestController
@RequestMapping(ApplicantConstants.APPLICANTS_BASE_PATH)
public class ApplicantController {

	@Autowired
	private ApplicantService applicantService;

	@PostMapping(ApplicantConstants.ADD)
	public ResponseEntity<Applicant> addApplicant(@RequestBody Applicant applicant) {
		Applicant addedApplicant = applicantService.addApplicant(applicant);
		return new ResponseEntity<>(addedApplicant, ApplicantConstants.HTTP_STATUS_CREATED);
	}

	@GetMapping(ApplicantConstants.GET_ALL)
	public ResponseEntity<List<Applicant>> getAllApplicants() throws ApplicantNotFoundException {
		List<Applicant> allApplicants = applicantService.getAllApplicants();
		return new ResponseEntity<>(allApplicants, ApplicantConstants.HTTP_STATUS_OK);
	}

	@GetMapping(ApplicantConstants.GET_BY_EMAIL)
	public ResponseEntity<Applicant> getApplicantByEmailId(@PathVariable String emailId)
			throws ApplicantNotFoundException {
		Applicant applicant = applicantService.getApplicantByEmailId(emailId);
		return new ResponseEntity<>(applicant, ApplicantConstants.HTTP_STATUS_OK);
	}

	
	@PutMapping(ApplicantConstants.UPDATE)
	public ResponseEntity<Applicant> updateApplicant(@RequestBody Applicant applicant)
			throws ApplicantNotFoundException {
		Applicant updatedApplicant = applicantService.updateApplicant(applicant);
		return new ResponseEntity<>(updatedApplicant, ApplicantConstants.HTTP_STATUS_OK);
	}

	@DeleteMapping(ApplicantConstants.DELETE)
	public ResponseEntity<String> deleteApplicant(@PathVariable String emailId) throws ApplicantNotFoundException {
		String deleteApplicant = applicantService.deleteApplicant(emailId);
		return new ResponseEntity<>(deleteApplicant, ApplicantConstants.HTTP_STATUS_OK);
	}

	@GetMapping(ApplicantConstants.GET_EDUCATION_ALL)
	public ResponseEntity<List<Education>> getAllEducation(@PathVariable String emailId) {
		List<Education> allEducation = applicantService.getAllEducation(emailId);
		return new ResponseEntity<>(allEducation, ApplicantConstants.HTTP_STATUS_OK);
	}

	@PostMapping(ApplicantConstants.ADD_EDUCATION)
	public ResponseEntity<List<Education>> addEducation(@PathVariable String emailId,
			@RequestBody Education education) {
		List<Education> updatedEducation = applicantService.addEducation(emailId, education);
		return new ResponseEntity<>(updatedEducation, ApplicantConstants.HTTP_STATUS_OK);
	}

	@GetMapping(ApplicantConstants.GET_EDUCATION)
	public ResponseEntity<Education> getEducation(@PathVariable String emailId, @PathVariable String degree) {
		Education education = applicantService.getEducation(emailId, degree);
		return new ResponseEntity<>(education, ApplicantConstants.HTTP_STATUS_OK);
	}

	@PutMapping(ApplicantConstants.UPDATE_EDUCATION)
	public ResponseEntity<List<Education>> updateEducation(@PathVariable String emailId,
			@RequestBody Education education) {
		List<Education> updatedEducation = applicantService.updateEducation(emailId, education);
		return new ResponseEntity<>(updatedEducation, ApplicantConstants.HTTP_STATUS_OK);
	}

	@DeleteMapping(ApplicantConstants.DELETE_EDUCATION)
	public ResponseEntity<String> deleteEducation(@PathVariable String emailId, @PathVariable long id) {
		String deleteMessage = applicantService.deleteEducation(emailId, id);
		return new ResponseEntity<>(deleteMessage, ApplicantConstants.HTTP_STATUS_OK);
	}

	@GetMapping(ApplicantConstants.GET_WORK_EXPERIENCE_ALL)
	public ResponseEntity<List<WorkExperience>> getAllWorkExperience(@PathVariable String emailId) {
		List<WorkExperience> allWorkExperience = applicantService.getAllWorkExperience(emailId);
		return new ResponseEntity<>(allWorkExperience, ApplicantConstants.HTTP_STATUS_OK);
	}

	@PostMapping(ApplicantConstants.ADD_WORK_EXPERIENCE)
	public ResponseEntity<List<WorkExperience>> addWorkExperience(@PathVariable String emailId,
			@RequestBody WorkExperience experience) {
		List<WorkExperience> updatedWorkExperience = applicantService.addWorkExperience(emailId, experience);
		return new ResponseEntity<>(updatedWorkExperience, ApplicantConstants.HTTP_STATUS_OK);
	}

	@GetMapping(ApplicantConstants.GET_WORK_EXPERIENCE)
	public ResponseEntity<WorkExperience> getWorkExperience(@PathVariable String emailId,
			@PathVariable String company) {
		WorkExperience workExperience = applicantService.getWorkExperience(emailId, company);
		return new ResponseEntity<>(workExperience, ApplicantConstants.HTTP_STATUS_OK);
	}

	@PutMapping(ApplicantConstants.UPDATE_WORK_EXPERIENCE)
	public ResponseEntity<List<WorkExperience>> updateWorkExperience(@PathVariable String emailId,
			@RequestBody WorkExperience experience) {
		List<WorkExperience> updatedWorkExperience = applicantService.updateWorkExperience(emailId, experience);
		return new ResponseEntity<>(updatedWorkExperience, ApplicantConstants.HTTP_STATUS_OK);
	}

	@DeleteMapping(ApplicantConstants.DELETE_WORK_EXPERIENCE)
	public ResponseEntity<String> deleteWorkExperience(@PathVariable String emailId, @PathVariable long id) {
		String deleteMessage = applicantService.deleteWorkExperience(emailId, id);
		return new ResponseEntity<>(deleteMessage, ApplicantConstants.HTTP_STATUS_OK);
	}
}
