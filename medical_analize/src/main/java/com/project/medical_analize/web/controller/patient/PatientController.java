package com.project.medical_analize.web.controller.patient;

import com.project.medical_analize.facade.PatientFacade;
import com.project.medical_analize.web.dto.request.PatientRequestDto;
import com.project.medical_analize.web.dto.response.PatientResponseDto;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientFacade patientFacade;

    public PatientController(PatientFacade patientFacade) {
        this.patientFacade = patientFacade;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "pages/patient/dashboard";
    }

    @GetMapping("/profile/{id}")
    public String profile( @PathVariable Long id ,Model model) {
        PatientResponseDto patientResponseDto = patientFacade.findById(id);
        model.addAttribute("patient", patientResponseDto);
        return "pages/patient/profile";
    }

    @GetMapping("/profile/edit/{id}")
    public String profileEdit(@PathVariable Long id, Model model) {
        PatientResponseDto patientResponseDto = patientFacade.findById(id);
        model.addAttribute("patient", patientResponseDto);
        return "pages/patient/profile_edit";
    }


    @PostMapping("/profile/edit/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("patient") PatientRequestDto patientRequestDto) {
        patientFacade.update(patientRequestDto, id);
        return "redirect:/patients/dashboard";
    }
}