package com.authservice.proxyentity.user;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.exception.ApplicantNotFoundException;
import com.authservice.exception.RecruiterNotFoundException;

@FeignClient(name = "USER-SERVICE", url = "http://localhost:8081/user")
public interface UserProxyController {

	@PostMapping(ApplicantConstants.ADD)
	public ResponseEntity<Applicant> addApplicant(@RequestBody Applicant applicant);

	@GetMapping(ApplicantConstants.GET_ALL)
	public ResponseEntity<List<Applicant>> getAllApplicants() throws ApplicantNotFoundException;

	@GetMapping(ApplicantConstants.GET_BY_ID)
	public ResponseEntity<Applicant> getApplicantByEmailId(@PathVariable String emailId)
			throws ApplicantNotFoundException;

	@PutMapping(ApplicantConstants.UPDATE)
	public ResponseEntity<Applicant> updateApplicant(@RequestBody Applicant applicant)
			throws ApplicantNotFoundException;

	@DeleteMapping(ApplicantConstants.DELETE)
	public ResponseEntity<String> deleteApplicant(@PathVariable String emailId) throws ApplicantNotFoundException;

	@PostMapping(RecruiterConstants.ADD)
	public ResponseEntity<Recruiter> addRecruiter(@RequestBody Recruiter recruiter);

	@GetMapping(RecruiterConstants.GET_ALL)
	public ResponseEntity<List<Recruiter>> getAllRecruiters() throws RecruiterNotFoundException;

	@GetMapping(RecruiterConstants.GET_BY_ID)
	public ResponseEntity<Recruiter> getRecruiterByEmailId(@PathVariable String emailId)
			throws RecruiterNotFoundException;

	@PutMapping(RecruiterConstants.UPDATE)
	public ResponseEntity<Recruiter> updateRecruiter(@RequestBody Recruiter recruiter)
			throws RecruiterNotFoundException;

	@DeleteMapping(RecruiterConstants.DELETE)
	public ResponseEntity<String> deleteRecruiter(@PathVariable String emailId) throws RecruiterNotFoundException;

	@GetMapping(ApplicantConstants.GET_EDUCATION_ALL)
	public ResponseEntity<List<Education>> getAllEducation(@PathVariable String emailId);

	@PostMapping(ApplicantConstants.ADD_EDUCATION)
	public ResponseEntity<List<Education>> addEducation(@PathVariable String emailId, @RequestBody Education education);

	@GetMapping(ApplicantConstants.GET_EDUCATION)
	public ResponseEntity<Education> getEducation(@PathVariable String emailId, @PathVariable String degree);

	@PutMapping(ApplicantConstants.UPDATE_EDUCATION)
	public ResponseEntity<List<Education>> updateEducation(@PathVariable String emailId,
			@RequestBody Education education);

	@DeleteMapping(ApplicantConstants.DELETE_EDUCATION)
	public ResponseEntity<String> deleteEducation(@PathVariable String emailId, @PathVariable String degree);

	@GetMapping(ApplicantConstants.GET_WORK_EXPERIENCE_ALL)
	public ResponseEntity<List<WorkExperience>> getAllWorkExperience(@PathVariable String emailId);

	@PostMapping(ApplicantConstants.ADD_WORK_EXPERIENCE)
	public ResponseEntity<List<WorkExperience>> addWorkExperience(@PathVariable String emailId,
			@RequestBody WorkExperience experience);

	@GetMapping(ApplicantConstants.GET_WORK_EXPERIENCE)
	public ResponseEntity<WorkExperience> getWorkExperience(@PathVariable String emailId, @PathVariable String company);

	@PutMapping(ApplicantConstants.UPDATE_WORK_EXPERIENCE)
	public ResponseEntity<List<WorkExperience>> updateWorkExperience(@PathVariable String emailId,
			@RequestBody WorkExperience experience);

	@DeleteMapping(ApplicantConstants.DELETE_WORK_EXPERIENCE)
	public ResponseEntity<String> deleteWorkExperience(@PathVariable String emailId, @PathVariable String company);

}
