package com.project.medicalanalize.web.controller.patient;

import com.project.medicalanalize.facade.PatientFacade;
import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.web.dto.request.PatientRequestDto;
import com.project.medicalanalize.web.dto.response.PatientResponseDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientFacade patientFacade;
    private final UserFacade userFacade;

    public PatientController(PatientFacade patientFacade, UserFacade userFacade) {
        this.patientFacade = patientFacade;
        this.userFacade = userFacade;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "pages/patient/patient_dashboard";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        User user = userFacade.getCurrentUser();
        PatientResponseDto patientResponseDto = patientFacade.findById(user.getId());
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
    public String updatePatient(@PathVariable Long id, @ModelAttribute("patient") PatientRequestDto patientRequestDto) throws ParseException {
        patientFacade.update(patientRequestDto, id);
        return "redirect:/patients/dashboard";
    }
}