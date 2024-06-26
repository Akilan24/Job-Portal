package com.authservice.proxyentity.job;

import org.springframework.http.HttpStatus;

public class PostConstants {

	public static final String DESCRIPTION_REQUIRED = "Please provide the description";
	public static final String EMAIL_VALID = "Please provide a valid email address";

	public static final String POST_BASE_URL = "/posts";
	public static final String ADD_POST = "/addpost";
	public static final String GET_ALL_POSTS = "/getallposts";
	public static final String GET_POST_BY_ID = "/getpostbyid/{id}";
	public static final String UPDATE_POST = "/updatepost";
	public static final String DELETE_POST = "/deletebyid/{id}";

	public static final HttpStatus HTTP_STATUS_CREATED = HttpStatus.CREATED;
    public static final HttpStatus HTTP_STATUS_OK = HttpStatus.OK;
    public static final HttpStatus HTTP_STATUS_NOT_FOUND = HttpStatus.NOT_FOUND;
    public static final HttpStatus METHOD_NOT_ALLOWED = HttpStatus.METHOD_NOT_ALLOWED;
    public static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

}
