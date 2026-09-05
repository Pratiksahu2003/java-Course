package com.vedmint.www.addjob.config;

import com.vedmint.www.addjob.entity.Job;
import com.vedmint.www.addjob.entity.JobType;
import com.vedmint.www.addjob.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final JobRepository jobRepository;

    @Override
    public void run(String... args) {
        if (jobRepository.count() > 0) {
            return;
        }

        jobRepository.save(Job.builder()
                .title("Senior Java Developer")
                .company("TechVedmint")
                .location("Bangalore, India")
                .jobType(JobType.FULL_TIME)
                .salary("₹12–18 LPA")
                .description("""
                        We are looking for an experienced Java Developer to join our backend team.
                        You will design and build scalable REST APIs, work with Spring Boot microservices,
                        and collaborate with product and frontend teams to deliver high-quality software.""")
                .requirements("""
                        - 4+ years of Java/Spring Boot experience
                        - Strong knowledge of REST APIs and SQL databases
                        - Experience with Git, Maven, and CI/CD pipelines
                        - Good problem-solving and communication skills""")
                .active(true)
                .build());

        jobRepository.save(Job.builder()
                .title("Frontend Intern")
                .company("StartupHub")
                .location("Remote")
                .jobType(JobType.INTERNSHIP)
                .salary("₹15,000/month stipend")
                .description("""
                        Join our growing team as a Frontend Intern and work on real-world React projects.
                        You'll learn modern UI development, component design, and how to ship features
                        in an agile environment with mentorship from senior engineers.""")
                .requirements("""
                        - Basic knowledge of HTML, CSS, and JavaScript
                        - Familiarity with React is a plus
                        - Eagerness to learn and take feedback
                        - Available for at least 3 months""")
                .active(true)
                .build());
    }
}
