package com.authservice.proxyentity.job;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Application")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long applicationId;

	@Email(message=ApplicationConstant.EMAIL_VALID)
	private String applicantEmailId;
	
	private String name;

	private String company;
	
	private String jobTitle;
	
	@Pattern(regexp = "^(Shortlisted|Rejected|Submitted)$", message = ApplicationConstant.VALID_STATUS_MESSAGE)
	private String status;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message=ApplicationConstant.APPLICATION_DATE_REQUIRED)
	private Date appliedDate;

}
