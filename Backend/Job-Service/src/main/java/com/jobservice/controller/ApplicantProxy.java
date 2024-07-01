package com.jobservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jobservice.constant.ApplicantConstants;
import com.jobservice.exception.ApplicantNotFoundException;
import com.jobservice.model.Applicant;

@FeignClient(name = "User-Service", url = "http://localhost:8084/user/applicants")
public interface ApplicantProxy {

	@GetMapping("/get/{id}")
	public Applicant getApplicantById(@PathVariable long id) throws ApplicantNotFoundException;

	@GetMapping(ApplicantConstants.GET_BY_ID)
	public Applicant getApplicantByEmailId(@PathVariable String emailId)
			throws ApplicantNotFoundException;
}
