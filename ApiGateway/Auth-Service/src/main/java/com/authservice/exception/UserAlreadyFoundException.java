package com.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserAlreadyFoundException extends RuntimeException {

	public UserAlreadyFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
