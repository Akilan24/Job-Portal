package com.userservice.constant;

import org.springframework.http.HttpStatus;

public class ApplicantConstants {

    // Base Paths
    public static final String APPLICANTS_BASE_PATH = "/User";

    // Endpoints
    public static final String ADD = "/addApplicant";
    public static final String GET_ALL = "/getallApplicant";
    public static final String GET_BY_EMAIL = "/getApplicantbyemailId/{emailId}";
    public static final String UPDATE = "/updateApplicant";
    public static final String DELETE = "/deleteApplicant/{emailId}";

    // HTTP Statuses
    public static final HttpStatus HTTP_STATUS_CREATED = HttpStatus.CREATED;
    public static final HttpStatus HTTP_STATUS_OK = HttpStatus.OK;
    public static final HttpStatus HTTP_STATUS_NOT_FOUND = HttpStatus.NOT_FOUND;

    // Validation Messages
    public static final String USERNAME_PATTERN = "Username should be alphanumeric and 6 characters long";
    public static final String PASSWORD_PATTERN = "Password must contain at least one digit, one lowercase and uppercase letter, one special character, and be at least 8 characters long";
    public static final String ROLE_PATTERN = "Please provide a valid role";
    public static final String NAME_REQUIRED = "Please provide the applicant name";
    public static final String EMAIL_VALID = "Please provide a valid email address";
    public static final String CITY_REQUIRED = "Please provide the city";
    public static final String STATE_REQUIRED = "Please provide the state";
    public static final String COUNTRY_REQUIRED = "Please provide the country";
    public static final String PINCODE_REQUIRED = "Please provide the pincode";
    public static final String MOBILE_NO_REQUIRED="Please provide the mobile number";
    public static final String STATUS_PATTERN = "Please provide a valid status";
    public static final String DEGREE_REQUIRED = "Please provide the degree";
    
    // Education endpoints
    public static final String GET_EDUCATION_ALL = "/getAllEducation/{emailId}";
    public static final String ADD_EDUCATION = "/addEducation/{emailId}";
    public static final String GET_EDUCATION = "/getEducation/{emailId}/{degree}";
    public static final String UPDATE_EDUCATION = "/updateEducation/{emailId}";
    public static final String DELETE_EDUCATION = "/deleteEducation/{emailId}/{id}";

    // WorkExperience endpoints
    public static final String GET_WORK_EXPERIENCE_ALL = "/getAllWorkExperience/{emailId}";
    public static final String ADD_WORK_EXPERIENCE = "/addWorkExperience/{emailId}";
    public static final String GET_WORK_EXPERIENCE = "/getWorkExperience/{emailId}/{company}";
    public static final String UPDATE_WORK_EXPERIENCE = "/updateWorkExperience/{emailId}";
    public static final String DELETE_WORK_EXPERIENCE = "/deleteWorkExperience/{emailId}/{id}";
    
 // WorkExperience validation messages
    public static final String COMPANY_REQUIRED = "Please provide the company name";
    public static final String POSITION_REQUIRED = "Please provide the position";

    
}
