package com.authservice.proxyentity.job;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobDetails")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jobId;

    @NotBlank(message = JobConstant.JOB_TITLE_REQUIRED)
    private String jobTitle;

    @NotBlank(message = JobConstant.JOB_DESCRIPTION_REQUIRED)
    private String jobDescription;

    @NotBlank(message = JobConstant.COMPANY_NAME_REQUIRED)
    private String company;

    @NotNull(message = JobConstant.SALARY_REQUIRED)
    private double salary;

    @Min(value = 0, message = JobConstant.EXPERIENCE_NON_NEGATIVE)
    private int experience;

    @Pattern(regexp = "^(Full Time|Part Time|Permanent|Temporary|Internship|Freelance)$", message = JobConstant.VALID_JOB_TYPE)
    private String jobType;

    @NotBlank(message = JobConstant.JOB_CATEGORY_REQUIRED)
    private String jobCategory;

    @NotBlank(message = JobConstant.CITY_REQUIRED)
    private String city;

    @NotBlank(message = JobConstant.STATE_REQUIRED)
    private String state;

    @NotBlank(message = JobConstant.COUNTRY_REQUIRED)
    private String country;

    @NotBlank(message = JobConstant.PINCODE_REQUIRED)
    private String pincode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate postedDate;

    private String requiredSkills;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "jobId", referencedColumnName = "jobId")
    private Set<Application> applications;
}
