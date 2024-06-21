package com.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping(RecruiterConstants.ADD)
	public ResponseEntity<Recruiter> addRecruiter(@RequestBody Recruiter recruiter) {
		return userProxy.addRecruiter(recruiter);
	}

	@GetMapping(RecruiterConstants.GET_ALL)
	public ResponseEntity<List<Recruiter>> getAllRecruiters() throws RecruiterNotFoundException {
		return userProxy.getAllRecruiters();
	}

	@GetMapping(RecruiterConstants.GET_BY_ID)
	public ResponseEntity<Recruiter> getRecruiterById(@PathVariable long id) throws RecruiterNotFoundException {
		return userProxy.getRecruiterById(id);
	}

	@PutMapping(RecruiterConstants.UPDATE)
	public ResponseEntity<Recruiter> updateRecruiter(@RequestBody Recruiter recruiter)
			throws RecruiterNotFoundException {
		return userProxy.updateRecruiter(recruiter);
	}

	@DeleteMapping(RecruiterConstants.DELETE)
	public ResponseEntity<String> deleteRecruiter(@PathVariable long id) throws RecruiterNotFoundException {
		return userProxy.deleteRecruiter(id);
	}
	
	@PostMapping(ApplicantConstants.ADD)
	public ResponseEntity<Applicant> addApplicant(@RequestBody Applicant applicant) {
		return userProxy.addApplicant(applicant);
	}

	@GetMapping(ApplicantConstants.GET_ALL)
	public ResponseEntity<List<Applicant>> getAllApplicants() throws ApplicantNotFoundException {
		return userProxy.getAllApplicants();
	}

	@GetMapping(ApplicantConstants.GET_BY_ID)
	public ResponseEntity<Applicant> getApplicantById(@PathVariable long id) throws ApplicantNotFoundException {
		return userProxy.getApplicantById(id);
	}

	@PutMapping(ApplicantConstants.UPDATE)
	public ResponseEntity<Applicant> updateApplicant(@RequestBody Applicant applicant)
			throws ApplicantNotFoundException {
		return userProxy.updateApplicant(applicant);
	}

	@DeleteMapping(ApplicantConstants.DELETE)
	public ResponseEntity<String> deleteApplicant(@PathVariable long id) throws ApplicantNotFoundException {
		return userProxy.deleteApplicant(id);
	}
}
