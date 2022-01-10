package com.project.medicalanalize.web.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient/analyzes")
public class PatientAnalyzeController {

    @GetMapping("/all/temp")
    public String tempAnalyses(Model model) {
//        model.addAttribute("analyses", new AnalysesRequestDto());
        return "pages/patient/analyzes/loaded_analyses";
    }

    @PostMapping("/all/temp")
    public String uploadAnalyses(Model model) {
        return "redirect:/patients/dashboard";
    }

    @GetMapping("/all/examples")
    public String example(Model model){
        return "pages/patient/analyzes/example";
    }
}