package com.project.medicalanalize.web.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient/analyzes")
public class PatientAnalyzeController {

    @GetMapping("/all/temp")
    public String tempAnalyses(Model model) {
        return "pages/patient/analyzes/type_analysis";
    }

    @GetMapping("/all/examples")
    public String examplesAnalyses(Model model) {
        return "pages/patient/analyzes/example";
    }

    @GetMapping("/blood")
    public String blood(Model model){
        return "pages/patient/analyzes/types_analysis/blood_test";
    }

    @GetMapping("/urine")
    public String urine(Model model){
        return "pages/patient/analyzes/types_analysis/urine_analysis";
    }

    @GetMapping("/fecal")
    public String fecal(Model model){
        return "pages/patient/analyzes/types_analysis/fecal_analysis";
    }

    @GetMapping("/cytological")
    public String cytological(Model model){
        return "pages/patient/analyzes/types_analysis/cytological_analysis";
    }
}