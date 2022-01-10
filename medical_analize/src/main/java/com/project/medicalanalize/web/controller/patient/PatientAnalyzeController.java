package com.project.medicalanalize.web.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient/analyzes")
public class PatientAnalyzeController {

    @GetMapping("/all/temp")
    public String temp(Model model) {
        return "pages/patient/analyzes/loaded_analyses";
    }

    @GetMapping("/all/examples")
    public String example(Model model){
        return "pages/patient/analyzes/example";
    }
}