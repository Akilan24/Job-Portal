package com.authservice.proxyentity.user;

import java.io.File;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = "^(?=.*\\d)[a-zA-Z0-9]{6}$", message = RecruiterConstants.USERNAME_PATTERN)
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = RecruiterConstants.PASSWORD_PATTERN)
    private String password;

    @Pattern(regexp = "^(recruiter)$", message = RecruiterConstants.ROLE_PATTERN)
    private String role;

    @NotBlank(message = RecruiterConstants.FIRST_NAME_REQUIRED)
    private String firstName;

    @NotBlank(message = RecruiterConstants.LAST_NAME_REQUIRED)
    private String lastName;

    @Email(message = RecruiterConstants.EMAIL_VALID)
    private String emailId;

    @NotBlank(message = RecruiterConstants.COMPANY_NAME_REQUIRED)
    private String company;

    @NotBlank(message = RecruiterConstants.CITY_REQUIRED)
    private String city;

    @NotBlank(message = RecruiterConstants.STATE_REQUIRED)
    private String state;

    @NotBlank(message = RecruiterConstants.COUNTRY_REQUIRED)
    private String country;

    @NotBlank(message = RecruiterConstants.PINCODE_REQUIRED)
    private String pincode;

    private File logo;
}
