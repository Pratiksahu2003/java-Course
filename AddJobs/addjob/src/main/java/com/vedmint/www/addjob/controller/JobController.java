package com.vedmint.www.addjob.controller;

import com.vedmint.www.addjob.dto.JobPostForm;
import com.vedmint.www.addjob.entity.Job;
import com.vedmint.www.addjob.entity.JobType;
import com.vedmint.www.addjob.service.ApplicationService;
import com.vedmint.www.addjob.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;
    private final ApplicationService applicationService;

    @ModelAttribute("jobTypes")
    public JobType[] jobTypes() {
        return JobType.values();
    }

    @GetMapping("/post")
    public String showPostForm(Model model) {
        if (!model.containsAttribute("jobPostForm")) {
            model.addAttribute("jobPostForm", new JobPostForm());
        }
        return "jobs/post";
    }

    @PostMapping("/post")
    public String postJob(@Valid @ModelAttribute("jobPostForm") JobPostForm form,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "jobs/post";
        }

        Job job = jobService.createJob(form);
        redirectAttributes.addFlashAttribute("successMessage",
                "Job posted successfully! Candidates can now apply.");
        return "redirect:/jobs/" + job.getId();
    }

    @GetMapping("/{id}")
    public String viewJob(@PathVariable Long id, Model model) {
        Job job = jobService.getActiveJobById(id);
        model.addAttribute("job", job);
        model.addAttribute("applicationCount", applicationService.getApplicationsForJob(id).size());
        return "jobs/detail";
    }
}
