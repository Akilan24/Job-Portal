package com.jobservice.constant;

import org.springframework.http.HttpStatus;

public class ApplicationConstant {

    public static final String APPLICATION = "/Job";
    public static final String ADD_APPLICATION = "/add/{jobId}/{emailId}";
    public static final String GET_ALL_APPLICATIONS = "/getall/{emailId}";
    public static final String GET_APPLICATION_BY_ID = "/get/{id}";
    public static final String UPDATE_APPLICATION = "/update";
    public static final String DELETE_APPLICATION = "/delete/{id}";

    public static final HttpStatus HTTPS_STATUS_CREATED = HttpStatus.CREATED;
    public static final HttpStatus HTTPS_STATUS_OK = HttpStatus.OK;
    public static final HttpStatus HTTPS_STATUS_NOT_FOUND = HttpStatus.NOT_FOUND;
    public static final HttpStatus METHOD_NOT_ALLOWED = HttpStatus.METHOD_NOT_ALLOWED;
    public static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;


    public static final String STATUS_REQUIRED = "Status is required";
    public static final String VALID_STATUS_MESSAGE = "Please provide a valid job type";
    public static final String APPLICATION_DATE_REQUIRED = "Application date is required";
    public static final String EMAIL_VALID = "Please provide a valid email address";
}
