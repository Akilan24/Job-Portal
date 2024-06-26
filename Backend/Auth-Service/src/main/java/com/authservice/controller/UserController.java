package com.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.exception.ApplicantNotFoundException;
import com.authservice.exception.RecruiterNotFoundException;
import com.authservice.proxyentity.user.Applicant;
import com.authservice.proxyentity.user.ApplicantConstants;
import com.authservice.proxyentity.user.Recruiter;
import com.authservice.proxyentity.user.RecruiterConstants;
import com.authservice.proxyentity.user.UserProxyController;

@RestController
@RequestMapping("/JP/User")
public class UserController {

	@Autowired
	private UserProxyController userProxy;

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@GetMapping(RecruiterConstants.GET_ALL)
	public ResponseEntity<List<Recruiter>> getAllRecruiters() throws RecruiterNotFoundException {
		return userProxy.getAllRecruiters();
	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@GetMapping(RecruiterConstants.GET_BY_ID)
	public ResponseEntity<Recruiter> getRecruiterById(@PathVariable String emailId) throws RecruiterNotFoundException {
		return userProxy.getRecruiterByEmailId(emailId);
	}

	@PreAuthorize("hasAuthority('recruiter')")
	@PutMapping(RecruiterConstants.UPDATE)
	public ResponseEntity<Recruiter> updateRecruiter(@RequestBody Recruiter recruiter)
			throws RecruiterNotFoundException {
		return userProxy.updateRecruiter(recruiter);
	}

	@PreAuthorize("hasAuthority('recruiter')")
	@DeleteMapping(RecruiterConstants.DELETE)
	public ResponseEntity<String> deleteRecruiter(@PathVariable String emailId) throws RecruiterNotFoundException {
		return userProxy.deleteRecruiter(emailId);
	}


	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@GetMapping(ApplicantConstants.GET_ALL)
	public ResponseEntity<List<Applicant>> getAllApplicants() throws ApplicantNotFoundException {
		return userProxy.getAllApplicants();
	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@GetMapping(ApplicantConstants.GET_BY_ID)
	public ResponseEntity<Applicant> getApplicantById(@PathVariable String emailId) throws ApplicantNotFoundException {
		return userProxy.getApplicantByEmailId(emailId);
	}

	@PreAuthorize("hasAuthority('applicant')")
	@PutMapping(ApplicantConstants.UPDATE)
	public ResponseEntity<Applicant> updateApplicant(@RequestBody Applicant applicant)
			throws ApplicantNotFoundException {
		return userProxy.updateApplicant(applicant);
	}

	@PreAuthorize("hasAuthority('applicant')")
	@DeleteMapping(ApplicantConstants.DELETE)
	public ResponseEntity<String> deleteApplicant(@PathVariable String emailId) throws ApplicantNotFoundException {
		return userProxy.deleteApplicant(emailId);
	}
}
