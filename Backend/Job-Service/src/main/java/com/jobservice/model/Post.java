package com.jobservice.model;

import com.jobservice.constant.PostConstants;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	@Email(message=PostConstants.EMAIL_VALID)
	private String emailId;
	
	@NotBlank(message=PostConstants.DESCRIPTION_REQUIRED)
	private String description;

}
