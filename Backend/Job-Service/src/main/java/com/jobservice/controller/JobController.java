package com.jobservice.controller;

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

import com.jobservice.constant.JobConstant;
import com.jobservice.exception.JobNotFoundException;
import com.jobservice.model.Job;
import com.jobservice.service.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(JobConstant.JOB)
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping(JobConstant.ADD_JOB)
    public ResponseEntity<Job> addJob(@RequestBody @Valid Job job) {
        Job addedJob = jobService.addJob(job);
        return new ResponseEntity<>(addedJob, JobConstant.HTTP_STATUS_CREATED);
    }

    @GetMapping(JobConstant.GET_ALL_JOBS)
    public ResponseEntity<List<Job>> getAllJobs() throws JobNotFoundException {
        List<Job> allJobs = jobService.getAllJob();
        return new ResponseEntity<>(allJobs, JobConstant.HTTP_STATUS_OK);
    }

    @GetMapping(JobConstant.GET_JOBS_BY_CATEGORY)
    public ResponseEntity<List<Job>> getJobsByCategory(@PathVariable String category) throws JobNotFoundException {
        List<Job> jobsByCategory = jobService.getByJobCategory(category);
        return new ResponseEntity<>(jobsByCategory, JobConstant.HTTP_STATUS_OK);
    }

    @GetMapping(JobConstant.GET_JOBS_BY_TYPE)
    public ResponseEntity<List<Job>> getJobsByType(@PathVariable String type) throws JobNotFoundException {
        List<Job> jobsByType = jobService.getByJobType(type);
        return new ResponseEntity<>(jobsByType, JobConstant.HTTP_STATUS_OK);
    }
    @GetMapping(JobConstant.GET_JOBS_BY_SEARCH)
    public ResponseEntity<List<Job>> getJobsBySearch(@PathVariable String search) throws JobNotFoundException {
        List<Job> jobsBySearch = jobService.getBySearch(search);
        return new ResponseEntity<>(jobsBySearch, JobConstant.HTTP_STATUS_OK);
    }
    @GetMapping(JobConstant.GET_JOBS_BY_ID)
    public ResponseEntity<Job> getJobsById(@PathVariable int jobId) throws JobNotFoundException {
        Job byJobId = jobService.getByJobId(jobId);
        return new ResponseEntity<>(byJobId, JobConstant.HTTP_STATUS_OK);
    }

    @PutMapping(JobConstant.UPDATE_JOB)
    public ResponseEntity<Job> updateJob(@RequestBody @Valid Job job) throws JobNotFoundException {
        Job updatedJob = jobService.updateJob(job);
        return new ResponseEntity<>(updatedJob, JobConstant.HTTP_STATUS_OK);
    }

    @DeleteMapping(JobConstant.DELETE_JOB)
    public ResponseEntity<String> deleteJob(@PathVariable long jobId) throws JobNotFoundException {
        String result = jobService.deleteJob(jobId);
        return new ResponseEntity<>(result, JobConstant.HTTP_STATUS_OK);
    }
}
