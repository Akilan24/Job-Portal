package com.authservice.proxyentity.job;

import org.springframework.http.HttpStatus;

public class JobConstant {

    // API Endpoints
    public static final String JOB = "/Job";
    public static final String ADD_JOB = "/addJob";
    public static final String GET_ALL_JOBS = "/getallJob";
    public static final String GET_JOBS_BY_CATEGORY = "/getJobbycategory/{category}";
    public static final String GET_JOBS_BY_TYPE = "/getJobbytype/{type}";
    public static final String GET_JOBS_BY_SALARY = "/getJobbysalary/{salary}";
    public static final String UPDATE_JOB = "/updateJob";
    public static final String DELETE_JOB = "/deleteJob/{jobId}";

    // HTTP Statuses
    public static final HttpStatus HTTP_STATUS_CREATED = HttpStatus.CREATED;
    public static final HttpStatus HTTP_STATUS_OK = HttpStatus.OK;
    public static final HttpStatus HTTP_STATUS_NOT_FOUND = HttpStatus.NOT_FOUND;
    public static final HttpStatus METHOD_NOT_ALLOWED = HttpStatus.METHOD_NOT_ALLOWED;
    public static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    // Validation Messages
    public static final String JOB_TITLE_REQUIRED = "Please provide a job title";
    public static final String JOB_DESCRIPTION_REQUIRED = "Please provide a job description";
    public static final String COMPANY_NAME_REQUIRED = "Please provide the company name";
    public static final String SALARY_REQUIRED = "Please provide the salary";
    public static final String EXPERIENCE_NON_NEGATIVE = "Experience must be non-negative";
    public static final String VALID_JOB_TYPE = "Please provide a valid job type";
    public static final String JOB_CATEGORY_REQUIRED = "Please provide a job category";
    public static final String CITY_REQUIRED = "Please provide the city";
    public static final String STATE_REQUIRED = "Please provide the state";
    public static final String COUNTRY_REQUIRED = "Please provide the country";
    public static final String PINCODE_REQUIRED = "Please provide the pincode";
    public static final String LOGO_REQUIRED = "Please provide the logo";


}
