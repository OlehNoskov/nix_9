package com.project.medicalanalize.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class HomePage {

    @GetMapping()
    public String dashboard() {
        return "pages/open/open_dashboard";
    }

    @GetMapping("/about")
    public String aboutUs() {
        return "pages/open/about_us";
    }

    @GetMapping("/articles")
    public String articles() {
        return "pages/open/articles";
    }

    @GetMapping("/feedback")
    public String feedback() {
        return "pages/open/feedback";
    }
}