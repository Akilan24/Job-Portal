package com.jobservice.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jobservice.constant.ApplicantConstants;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Entity
public class WorkExperience {
	//@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ExperienceId;
	@NotBlank(message=ApplicantConstants.COMPANY_REQUIRED)
	private String company;
	@NotBlank(message=ApplicantConstants.POSITION_REQUIRED)
	private String position;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

}
