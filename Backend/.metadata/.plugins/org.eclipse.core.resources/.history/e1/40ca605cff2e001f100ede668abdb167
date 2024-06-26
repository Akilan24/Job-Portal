package com.authservice.proxyentity.user;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long applicantId;

    @Pattern(regexp = "^(?=.*\\d)[a-zA-Z0-9]{6}$", message = ApplicantConstants.USERNAME_PATTERN)
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = ApplicantConstants.PASSWORD_PATTERN)
    private String password;

    @Pattern(regexp = "^(applicant)$", message = ApplicantConstants.ROLE_PATTERN)
    private String role;

    @NotBlank(message = ApplicantConstants.FIRST_NAME_REQUIRED)
    private String firstName;

    @NotBlank(message = ApplicantConstants.LAST_NAME_REQUIRED)
    private String lastName;

    @Email(message = ApplicantConstants.EMAIL_VALID)
    private String emailId;

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
    @JoinColumn(name = "applicantId", referencedColumnName = "applicantId")
    private List<WorkExperience> experience;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "applicantId", referencedColumnName = "applicantId")
    private List<Education> education;
}
