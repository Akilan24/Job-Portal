package com.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApplicantNotFoundException extends Exception {
	public ApplicantNotFoundException(String message) {
		super(message);
	}

}
