package com.vedmint.www.addjob.controller;

import com.vedmint.www.addjob.dto.JobApplicationForm;
import com.vedmint.www.addjob.entity.Job;
import com.vedmint.www.addjob.entity.JobApplication;
import com.vedmint.www.addjob.exception.DuplicateApplicationException;
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

import java.io.IOException;

@Controller
@RequestMapping("/jobs/{jobId}/apply")
@RequiredArgsConstructor
public class ApplicationController {

    private final JobService jobService;
    private final ApplicationService applicationService;

    @GetMapping
    public String showApplyForm(@PathVariable Long jobId, Model model) {
        Job job = jobService.getActiveJobById(jobId);
        model.addAttribute("job", job);
        if (!model.containsAttribute("applicationForm")) {
            model.addAttribute("applicationForm", new JobApplicationForm());
        }
        return "jobs/apply";
    }

    @PostMapping
    public String submitApplication(@PathVariable Long jobId,
                                    @Valid @ModelAttribute("applicationForm") JobApplicationForm form,
                                    BindingResult bindingResult,
                                    Model model,
                                    RedirectAttributes redirectAttributes) {
        Job job = jobService.getActiveJobById(jobId);
        model.addAttribute("job", job);

        if (bindingResult.hasErrors()) {
            return "jobs/apply";
        }

        try {
            JobApplication application = applicationService.submitApplication(jobId, form);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Your application has been submitted successfully!");
            redirectAttributes.addFlashAttribute("applicantName", application.getFullName());
            return "redirect:/jobs/" + jobId + "/apply/success";
        } catch (DuplicateApplicationException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "jobs/apply";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "jobs/apply";
        } catch (IOException ex) {
            model.addAttribute("errorMessage", "Failed to upload resume. Please try again.");
            return "jobs/apply";
        }
    }

    @GetMapping("/success")
    public String applicationSuccess(@PathVariable Long jobId, Model model) {
        Job job = jobService.getActiveJobById(jobId);
        model.addAttribute("job", job);
        return "jobs/apply-success";
    }
}
