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

    @GetMapping(ApplicantConstants.GET_BY_ID)
    public ResponseEntity<Applicant> getApplicantByEmailId(@PathVariable String emailId) throws ApplicantNotFoundException {
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
}
