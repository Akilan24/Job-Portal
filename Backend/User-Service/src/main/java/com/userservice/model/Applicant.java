package com.userservice.model;

import java.util.List;
import java.util.Set;

import com.userservice.constant.ApplicantConstants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {

	@Id
	@Email(message = ApplicantConstants.EMAIL_VALID)
	private String emailId;

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = ApplicantConstants.PASSWORD_PATTERN)
	private String password;

	@Pattern(regexp = "^(applicant)$", message = ApplicantConstants.ROLE_PATTERN)
	private String role;

	@NotBlank(message = ApplicantConstants.FIRST_NAME_REQUIRED)
	private String firstName;

	@NotBlank(message = ApplicantConstants.LAST_NAME_REQUIRED)
	private String lastName;
	
	@NotNull(message=ApplicantConstants.MOBILE_NO_REQUIRED)
	private long mobileNo;

	@NotBlank(message = ApplicantConstants.CITY_REQUIRED)
	private String city;

	@NotBlank(message = ApplicantConstants.STATE_REQUIRED)
	private String state;

	@NotBlank(message = ApplicantConstants.COUNTRY_REQUIRED)
	private String country;

	@NotBlank(message = ApplicantConstants.PINCODE_REQUIRED)
	private String pincode;

	private Set<String> Skills;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "emailId", referencedColumnName = "emailId")
	private List<WorkExperience> experience;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "emailId", referencedColumnName = "emailId")
	private List<Education> education;
	
	@Pattern(regexp="^(I'm experienced | I'm a fresher)$",message = ApplicantConstants.STATUS_PATTERN)
	private String workStatus;
}
