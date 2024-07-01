package com.authservice.service;

import org.springframework.stereotype.Service;

import com.authservice.exception.ApplicantNotFoundException;
import com.authservice.proxyentity.user.Applicant;
import com.authservice.proxyentity.user.Recruiter;
import com.authservice.request.AuthRequest;
import com.authservice.request.RefreshTokenRequest;
import com.authservice.response.JwtResponse;

import jakarta.mail.MessagingException;

@Service
public interface UserService {

	public String saveApplicant(Applicant applicant);
	
	public String saveRecruiter(Recruiter recruiter);
	
	public JwtResponse login(AuthRequest authRequest);

	public String logout(AuthRequest authRequest, String refreshToken);

	public Boolean forgotpassword(String toEmail) throws MessagingException, ApplicantNotFoundException;
	
	public JwtResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
