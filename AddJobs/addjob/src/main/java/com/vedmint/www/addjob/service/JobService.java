package com.vedmint.www.addjob.service;

import com.vedmint.www.addjob.dto.JobPostForm;
import com.vedmint.www.addjob.entity.Job;
import com.vedmint.www.addjob.exception.ResourceNotFoundException;
import com.vedmint.www.addjob.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    @Transactional(readOnly = true)
    public List<Job> getActiveJobs() {
        return jobRepository.findByActiveTrueOrderByPostedAtDesc();
    }

    @Transactional(readOnly = true)
    public Job getActiveJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        if (!job.isActive()) {
            throw new ResourceNotFoundException("Job not found");
        }
        return job;
    }

    @Transactional
    public Job createJob(JobPostForm form) {
        Job job = Job.builder()
                .title(form.getTitle().trim())
                .company(form.getCompany().trim())
                .location(form.getLocation().trim())
                .jobType(form.getJobType())
                .salary(form.getSalary().trim())
                .description(form.getDescription().trim())
                .requirements(form.getRequirements().trim())
                .active(true)
                .build();
        return jobRepository.save(job);
    }
}
