package com.jobservice.model;

import com.jobservice.constant.RecruiterConstants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Recruiter {

	
	@Email(message = RecruiterConstants.EMAIL_VALID)
	private String emailId;

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = RecruiterConstants.PASSWORD_PATTERN)
	private String password;

	@Pattern(regexp = "^(recruiter)$", message = RecruiterConstants.ROLE_PATTERN)
	private String role;

	@NotBlank(message = RecruiterConstants.COMPANY_NAME_REQUIRED)
	private String company;

	@NotBlank(message = RecruiterConstants.CITY_REQUIRED)
	private String city;

	private String about;
	
	@NotBlank(message = RecruiterConstants.STATE_REQUIRED)
	private String state;

	@NotBlank(message = RecruiterConstants.COUNTRY_REQUIRED)
	private String country;

	@NotBlank(message = RecruiterConstants.PINCODE_REQUIRED)
	private String pincode;

}
