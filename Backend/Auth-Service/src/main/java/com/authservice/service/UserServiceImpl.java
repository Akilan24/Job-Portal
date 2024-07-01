package com.authservice.service;


import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.authservice.exception.ApplicantNotFoundException;
import com.authservice.exception.AuthenticationFailedException;
import com.authservice.exception.UserAlreadyFoundException;
import com.authservice.proxyentity.user.Applicant;
import com.authservice.proxyentity.user.Recruiter;
import com.authservice.proxyentity.user.RefreshToken;
import com.authservice.proxyentity.user.UserProxyController;
import com.authservice.repository.ApplicantRepository;
import com.authservice.repository.RecruiterRepository;
import com.authservice.repository.RefreshTokenRepository;
import com.authservice.request.AuthRequest;
import com.authservice.request.RefreshTokenRequest;
import com.authservice.response.JwtResponse;
import com.authservice.utility.JwtUtility;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtility jwtutil;

	@Autowired
	RefreshTokenService refreshToken;

	@Autowired
	RefreshTokenRepository refreshTokenRepo;

	@Autowired
	ApplicantRepository applicantRepo;
	
	@Autowired
	UserProxyController userproxy;

	@Autowired
	RecruiterRepository recruiterRepo;

	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	String fromEmail;
	
	@Override
	public String saveApplicant(Applicant applicant) {
		if (!applicantRepo.findByEmailId(applicant.getEmailId()).isPresent()) {
			applicant.setPassword(passwordEncoder.encode(applicant.getPassword()));
			applicantRepo.save(applicant);
			return "Applicant added to the system";
		} else {
			throw new UserAlreadyFoundException("Username already taken. Try another.");
		}
	}

	@Override
	public String saveRecruiter(Recruiter recruiter) {
		if (!recruiterRepo.findByEmailId(recruiter.getEmailId()).isPresent()) {
			recruiter.setPassword(passwordEncoder.encode(recruiter.getPassword()));
			recruiterRepo.save(recruiter);
			return "Recruiter added to the system";
		} else {
			throw new UserAlreadyFoundException("Username already taken. Try another.");
		}
	}

	@Override
	public JwtResponse login(AuthRequest authRequest) {
		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmailId(), authRequest.getPassword()));

			List<RefreshToken> refreshTokenList = refreshTokenRepo.findAll().stream()
					.filter(t -> t.getUsername().equalsIgnoreCase(authRequest.getEmailId()))
					.collect(Collectors.toList());
			for (RefreshToken rt : refreshTokenList) {
				refreshTokenRepo.deleteById(rt.getId());
			}

			if (authentication.isAuthenticated()) {
				return JwtResponse.builder().accessToken(jwtutil.generateToken(authentication))
						.refreshToken(refreshToken.createRefreshToken(authRequest.getEmailId()).getRefreshToken())
						.build();
			} else {
				throw new AuthenticationFailedException("Wrong Credentials");
			}
		} catch (Exception e) {
			throw new AuthenticationFailedException(e.getMessage());
		}

	}

	@Override
	public String logout(AuthRequest authRequest, String refreshToken) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmailId(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			refreshTokenRepo.deleteById(refreshTokenRepo.findByRefreshToken(refreshToken).get().getId());
			return "Logged out Successfully";

		} else {
			throw new AuthenticationFailedException("Invalid Credentials");
		}
	}

	@Override
	public JwtResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		RefreshToken rt = refreshToken.findByToken(refreshTokenRequest.getRefreshToken()).get();
		Applicant r = applicantRepo.findByEmailId(rt.getUsername()).get();
		if (refreshToken.verifyExpiration(rt)) {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(r.getEmailId(), r.getPassword()));
			if (!authentication.isAuthenticated()) {
				throw new AuthenticationFailedException("Refresh token is not in database!");
			}
			return JwtResponse.builder().accessToken(jwtutil.generateToken(authentication))
					.refreshToken(refreshTokenRequest.getRefreshToken()).build();
		} else {
			throw new AuthenticationFailedException("Refresh token is not in database!");
		}

	}

	@Override
	public Boolean forgotpassword(String toEmail) throws MessagingException, ApplicantNotFoundException {
		Applicant reg = userproxy.getApplicantByEmailId(toEmail).getBody();
		if (reg != null) {
			String password = generatePassword(8, 12);
			reg.setPassword(password);
			applicantRepo.save(reg);
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			String emailBody = "<html><body><h3>Hi " + reg.getName() + ",</h3><p>Your reset password: <b>"
					+ password + "</b>. Use it to login and reset your password in the below link.</p>"
					+ "<a href=\'http://localhost:5173/resetpassword\'>Reset Password link</a></body></html>";

			helper.setText(emailBody, true);
			helper.setSubject("Coastal Serenity - Reset Password");
			helper.setFrom(fromEmail);
			helper.setTo(toEmail);

			mailSender.send(message);
			return true;

		}
		return false;
	}
	public static String generatePassword(int minLength, int maxLength) {
		String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
		String CHAR_UPPER = CHAR_LOWER.toUpperCase();
		String NUMBER = "0123456789";
		String SPECIAL_CHAR = "@#$%^&+=!";

		String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + SPECIAL_CHAR;
		SecureRandom random = new SecureRandom();
		if (minLength < 8 || maxLength < minLength) {
			throw new IllegalArgumentException("Invalid password length range");
		}

		int length = minLength + random.nextInt(maxLength - minLength + 1);

		StringBuilder password = new StringBuilder(length);
		password.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
		password.append(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));
		password.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
		password.append(SPECIAL_CHAR.charAt(random.nextInt(SPECIAL_CHAR.length())));

		for (int i = 4; i < length; i++) {
			password.append(PASSWORD_ALLOW_BASE.charAt(random.nextInt(PASSWORD_ALLOW_BASE.length())));
		}

		char[] passwordChars = password.toString().toCharArray();
		for (int i = 0; i < passwordChars.length; i++) {
			int randomIndex = random.nextInt(passwordChars.length);
			char temp = passwordChars[i];
			passwordChars[i] = passwordChars[randomIndex];
			passwordChars[randomIndex] = temp;
		}

		return new String(passwordChars);
	}

}
