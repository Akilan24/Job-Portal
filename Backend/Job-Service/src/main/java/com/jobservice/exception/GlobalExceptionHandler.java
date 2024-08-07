package com.jobservice.exception;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(JobNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(JobNotFoundException ex, WebRequest request) {

		ErrorResponse err = new ErrorResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false),
				"Bad Request");
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(ApplicationNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(ApplicationNotFoundException ex, WebRequest request) {

		ErrorResponse err = new ErrorResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false),
				"Bad Request");
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,

			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		StringBuilder details = new StringBuilder();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			details.append(error.getDefaultMessage()).append(".");
		}
		ErrorResponse response = new ErrorResponse(LocalDate.now(), details.toString(), request.getDescription(false),
				"Bad Request");
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {

		ErrorResponse exceptionResponse = new ErrorResponse(LocalDate.now(), ex.getMessage(),
				request.getDescription(false), "Internal Server Error");
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
