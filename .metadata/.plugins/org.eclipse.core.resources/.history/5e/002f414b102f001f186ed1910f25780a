package com.jobservice.controller;

import java.util.List;
import java.util.Optional;

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

import com.jobservice.constant.ApplicationConstant;
import com.jobservice.exception.ApplicantNotFoundException;
import com.jobservice.exception.ApplicationNotFoundException;
import com.jobservice.model.Application;
import com.jobservice.service.ApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApplicationConstant.APPLICATION)
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    @PostMapping(ApplicationConstant.ADD_APPLICATION)
    public ResponseEntity<Application> addJobApplication(@RequestBody @Valid Application application) throws ApplicantNotFoundException   {
        Application addedJobApplication = service.addJobApplication(application);
        return new ResponseEntity<>(addedJobApplication, ApplicationConstant.HTTPS_STATUS_CREATED);
    }

    @GetMapping(ApplicationConstant.GET_ALL_APPLICATIONS)
    public ResponseEntity<List<Application>> getAllJobApplications() throws ApplicationNotFoundException {
        List<Application> allJobApplications = service.getAllJobApplications();
        return new ResponseEntity<>(allJobApplications, ApplicationConstant.HTTPS_STATUS_OK);
    }

    @GetMapping(ApplicationConstant.GET_APPLICATION_BY_ID)
    public ResponseEntity<Optional<Application>> getJobApplicationById(@PathVariable long id)
            throws ApplicationNotFoundException {
        Optional<Application> application = service.getJobApplicationById(id);
        return new ResponseEntity<>(application, ApplicationConstant.HTTPS_STATUS_OK);
    }

    @PutMapping(ApplicationConstant.UPDATE_APPLICATION)
    public ResponseEntity<Application> updateJobApplication(@RequestBody @Valid Application application)
            throws ApplicationNotFoundException, ApplicantNotFoundException {
        Application updatedJobApplication = service.updateJobApplication(application);
        return new ResponseEntity<>(updatedJobApplication, ApplicationConstant.HTTPS_STATUS_OK);
    }

    @DeleteMapping(ApplicationConstant.DELETE_APPLICATION)
    public ResponseEntity<String> deleteJobApplication(@PathVariable long id) throws ApplicationNotFoundException {
        String result = service.deleteJobApplication(id);
        return new ResponseEntity<>(result, ApplicationConstant.HTTPS_STATUS_OK);
    }
}
