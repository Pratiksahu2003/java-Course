package com.vedmint.www.addjob.service;

import com.vedmint.www.addjob.dto.JobApplicationForm;
import com.vedmint.www.addjob.entity.Job;
import com.vedmint.www.addjob.entity.JobApplication;
import com.vedmint.www.addjob.exception.DuplicateApplicationException;
import com.vedmint.www.addjob.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private static final long MAX_RESUME_SIZE = 5 * 1024 * 1024; // 5 MB

    private final JobApplicationRepository applicationRepository;
    private final JobService jobService;

    @Transactional(readOnly = true)
    public List<JobApplication> getApplicationsForJob(Long jobId) {
        return applicationRepository.findByJobIdOrderByAppliedAtDesc(jobId);
    }

    @Transactional
    public JobApplication submitApplication(Long jobId, JobApplicationForm form) throws IOException {
        Job job = jobService.getActiveJobById(jobId);
        String email = form.getEmail().trim().toLowerCase();

        if (applicationRepository.existsByJobIdAndEmail(jobId, email)) {
            throw new DuplicateApplicationException("You have already applied for this job with this email.");
        }

        String resumeFileName = storeResume(form.getResume(), jobId);

        JobApplication application = JobApplication.builder()
                .job(job)
                .fullName(form.getFullName().trim())
                .email(email)
                .phone(form.getPhone().trim())
                .linkedIn(trimOrNull(form.getLinkedIn()))
                .coverLetter(trimOrNull(form.getCoverLetter()))
                .resumeFileName(resumeFileName)
                .build();

        return applicationRepository.save(application);
    }

    private String storeResume(MultipartFile resume, Long jobId) throws IOException {
        if (resume == null || resume.isEmpty()) {
            return null;
        }

        if (resume.getSize() > MAX_RESUME_SIZE) {
            throw new IllegalArgumentException("Resume file must be 5 MB or smaller.");
        }

        String originalName = resume.getOriginalFilename();
        if (originalName == null || originalName.isBlank()) {
            throw new IllegalArgumentException("Invalid resume file.");
        }

        String extension = getExtension(originalName).toLowerCase();
        if (!extension.equals(".pdf") && !extension.equals(".doc") && !extension.equals(".docx")) {
            throw new IllegalArgumentException("Resume must be a PDF, DOC, or DOCX file.");
        }

        Path uploadDir = Paths.get("uploads", "resumes", String.valueOf(jobId));
        Files.createDirectories(uploadDir);

        String storedName = UUID.randomUUID() + extension;
        Path target = uploadDir.resolve(storedName);
        Files.copy(resume.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        return storedName;
    }

    private String getExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        return dotIndex >= 0 ? filename.substring(dotIndex) : "";
    }

    private String trimOrNull(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
}
