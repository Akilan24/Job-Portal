package com.userservice.constant;

import org.springframework.http.HttpStatus;

public class RecruiterConstants {

    // Base Paths
    public static final String RECRUITERS_BASE_PATH = "/User";

    // Endpoints
    public static final String ADD = "/addRecruiter";
    public static final String GET_ALL = "/getallRecruiter";
    public static final String GET_BY_ID = "/getRecruiter/{id}";
    public static final String UPDATE = "/updateRecruiter";
    public static final String DELETE = "/deleteRecruiter/{id}";

    // HTTP Statuses
    public static final HttpStatus HTTP_STATUS_CREATED = HttpStatus.CREATED;
    public static final HttpStatus HTTP_STATUS_OK = HttpStatus.OK;
    public static final HttpStatus HTTP_STATUS_NOT_FOUND = HttpStatus.NOT_FOUND;

    // Validation Messages
    public static final String USERNAME_PATTERN = "Username should be alphanumeric and 6 characters long";
    public static final String PASSWORD_PATTERN = "Password must contain at least one digit, one lowercase and uppercase letter, one special character, and be at least 8 characters long";
    public static final String ROLE_PATTERN = "Please provide a valid role";
    public static final String FIRST_NAME_REQUIRED = "Please provide the first name";
    public static final String LAST_NAME_REQUIRED = "Please provide the last name";
    public static final String EMAIL_VALID = "Please provide a valid email address";
    public static final String COMPANY_NAME_REQUIRED = "Please provide the company name";
    public static final String CITY_REQUIRED = "Please provide the city";
    public static final String STATE_REQUIRED = "Please provide the state";
    public static final String COUNTRY_REQUIRED = "Please provide the country";
    public static final String PINCODE_REQUIRED = "Please provide the pincode";

    // Field Names
    public static final String RECRUITER_ID = "recruiterId";
    public static final String USER_ID = "userId";
}
