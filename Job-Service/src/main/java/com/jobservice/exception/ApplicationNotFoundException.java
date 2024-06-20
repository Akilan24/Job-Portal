package com.jobservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApplicationNotFoundException extends Exception {
	
	public ApplicationNotFoundException(String message) {
		super(message);
	}

}
