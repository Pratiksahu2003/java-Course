package com.vedmint.www.web1.App.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        model.addAttribute("message", "Welcome " + username + " to the home page" + password);
        return "index";
    }
}
