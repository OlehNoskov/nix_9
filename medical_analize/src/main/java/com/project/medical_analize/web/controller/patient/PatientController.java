package com.project.medical_analize.web.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "pages/patient/dashboard";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "pages/patient/profile";
    }

//    @GetMapping("/profile")
//    public String profile(Model model) {
//        return "pages/patient/profile_edit";
//    }

    @GetMapping("/profile/edit")
    public String profileEdit(Model model) {
        return "pages/patient/profile_edit";
    }
}
