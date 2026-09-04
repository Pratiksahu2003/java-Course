package com.vedmint.www.addjob.repository;

import com.vedmint.www.addjob.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByJobIdOrderByAppliedAtDesc(Long jobId);

    boolean existsByJobIdAndEmail(Long jobId, String email);
}
