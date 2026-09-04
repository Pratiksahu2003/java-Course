package com.vedmint.www.addjob.dto;

import com.vedmint.www.addjob.entity.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobPostForm {

    @NotBlank(message = "Job title is required")
    @Size(max = 150, message = "Title must be at most 150 characters")
    private String title;

    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company must be at most 100 characters")
    private String company;

    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Location must be at most 100 characters")
    private String location;

    @NotNull(message = "Job type is required")
    private JobType jobType;

    @NotBlank(message = "Salary is required")
    @Size(max = 50, message = "Salary must be at most 50 characters")
    private String salary;

    @NotBlank(message = "Job description is required")
    @Size(min = 50, message = "Description must be at least 50 characters")
    private String description;

    @NotBlank(message = "Requirements are required")
    @Size(min = 20, message = "Requirements must be at least 20 characters")
    private String requirements;
}
