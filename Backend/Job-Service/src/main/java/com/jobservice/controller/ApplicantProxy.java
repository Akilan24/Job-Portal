package com.jobservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jobservice.constant.ApplicantConstants;
import com.jobservice.exception.ApplicantNotFoundException;
import com.jobservice.model.Applicant;

@FeignClient(name = "User-Service", url = "http://localhost:8081/User")
public interface ApplicantProxy {

	@GetMapping(ApplicantConstants.GET_BY_ID)
	public Applicant getApplicantById(@PathVariable long id) throws ApplicantNotFoundException;

	@GetMapping(ApplicantConstants.GET_BY_EMAIL)
	public Applicant getApplicantByEmailId(@PathVariable String emailId)
			throws ApplicantNotFoundException;
}
