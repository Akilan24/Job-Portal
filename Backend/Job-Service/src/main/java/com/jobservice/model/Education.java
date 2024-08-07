package com.jobservice.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jobservice.constant.ApplicantConstants;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {
	
	private long qualificationId;
	@NotBlank(message = ApplicantConstants.DEGREE_REQUIRED)
	private String degree;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

}
