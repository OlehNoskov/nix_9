package com.project.medical_analize.web.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @GetMapping
    public String dashboard(Model model) {
        return "pages/patient/dashboard";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "pages/patient/profile";
    }
}
