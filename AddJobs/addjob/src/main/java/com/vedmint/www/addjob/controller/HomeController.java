package com.vedmint.www.addjob.controller;

import com.vedmint.www.addjob.entity.Job;
import com.vedmint.www.addjob.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final JobService jobService;

    @GetMapping("/")
    public String home(Model model) {
        List<Job> jobs = jobService.getActiveJobs();
        model.addAttribute("jobs", jobs);
        return "index";
    }
}
