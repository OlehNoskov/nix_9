package com.project.medical_analize.web.controller.doctor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

        @GetMapping("/dashboard")
        public String dashboard(Model model) {
            return "pages/doctor/dashboard";
        }

        @GetMapping("/profile")
        public String profile(Model model) {
            return "pages/doctor/profile";
        }
}
