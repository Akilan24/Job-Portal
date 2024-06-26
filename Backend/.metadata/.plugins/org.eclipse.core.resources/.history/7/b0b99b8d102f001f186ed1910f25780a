package com.jobservice.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jobservice.constant.ApplicationConstant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

	@NotNull(message=ApplicationConstant.USER_ID_REQUIRED)
	private long userId;

	@Pattern(regexp = "^(Shortlisted|Rejected|Closed|In progress|Submitted)$", message = ApplicationConstant.VALID_STATUS_MESSAGE)
	private String status;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message=ApplicationConstant.APPLICATION_DATE_REQUIRED)
	private Date appliedDate;

}
