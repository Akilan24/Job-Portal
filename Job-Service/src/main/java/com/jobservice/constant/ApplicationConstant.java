package com.jobservice.constant;

import org.springframework.http.HttpStatus;

public class ApplicationConstant {

    public static final String APPLICATION = "/Job";
    public static final String ADD_APPLICATION = "/addApplication";
    public static final String GET_ALL_APPLICATIONS = "/getallApplication";
    public static final String GET_APPLICATION_BY_ID = "/getApplication/{id}";
    public static final String UPDATE_APPLICATION = "/updateApplication";
    public static final String DELETE_APPLICATION = "/deleteApplication/{id}";

    public static final HttpStatus HTTPS_STATUS_CREATED = HttpStatus.CREATED;
    public static final HttpStatus HTTPS_STATUS_OK = HttpStatus.OK;
    public static final HttpStatus HTTPS_STATUS_NOT_FOUND = HttpStatus.NOT_FOUND;
    public static final HttpStatus METHOD_NOT_ALLOWED = HttpStatus.METHOD_NOT_ALLOWED;
    public static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    public static final String USER_ID_REQUIRED = "User ID is required";
    public static final String STATUS_REQUIRED = "Status is required";
    public static final String VALID_STATUS_MESSAGE = "Please provide a valid job type";
    public static final String APPLICATION_DATE_REQUIRED = "Application date is required";
}
