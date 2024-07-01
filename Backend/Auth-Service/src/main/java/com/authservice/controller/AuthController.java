package com.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.exception.ApplicantNotFoundException;
import com.authservice.proxyentity.user.Applicant;
import com.authservice.proxyentity.user.Recruiter;
import com.authservice.request.AuthRequest;
import com.authservice.request.RefreshTokenRequest;
import com.authservice.response.JwtResponse;
import com.authservice.service.UserService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/JP/Auth")
@CrossOrigin("http://localhost:5173")
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("/addApplicant")
	public ResponseEntity<String> addApplicant(@RequestBody Applicant applicant) {
		return new ResponseEntity<>(userService.saveApplicant(applicant), HttpStatus.OK);
	}

	@PostMapping("/addRecruiter")
	public ResponseEntity<String> addRecruiter(@RequestBody Recruiter recruiter) {
		return new ResponseEntity<>(userService.saveRecruiter(recruiter), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody AuthRequest authRequest) {
		return new ResponseEntity<>(userService.login(authRequest), HttpStatus.OK);

	}

	@PostMapping("/forgotpassword/{toEmail}")
	public ResponseEntity<Boolean> forgotpassword(@PathVariable String toEmail) throws MessagingException, ApplicantNotFoundException {
		return new ResponseEntity<>(userService.forgotpassword(toEmail), HttpStatus.OK);
	}
	
	@PostMapping("/logout/{refreshToken}")
	public ResponseEntity<String> logout(@RequestBody AuthRequest authRequest, @PathVariable String refreshToken) {
		return new ResponseEntity<>(userService.logout(authRequest, refreshToken), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyAuthority('recruiter','applicant')")
	@PostMapping("/refreshToken")
	public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		return new ResponseEntity<>(userService.refreshToken(refreshTokenRequest), HttpStatus.OK);
	}

}
