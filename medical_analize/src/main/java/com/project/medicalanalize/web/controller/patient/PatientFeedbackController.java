package com.project.medicalanalize.web.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient/feedback")
public class PatientFeedbackController {

    @GetMapping
    public String dashboard(Model model) {
        return "pages/patient/feedback/feedback_all";
    }

    @GetMapping("/new")
    public String redirectToNew(Model model) {
        return "pages/patient/feedback/feedback_new";
    }
}
