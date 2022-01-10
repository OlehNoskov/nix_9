package com.project.medicalanalize.web.controller.doctor;

import com.project.medicalanalize.facade.DoctorFacade;
import com.project.medicalanalize.web.dto.request.DoctorRequestDto;
import com.project.medicalanalize.web.dto.response.DoctorResponseDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorFacade doctorFacade;

    public DoctorController(DoctorFacade doctorFacade) {
        this.doctorFacade = doctorFacade;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "pages/doctor/dashboard";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id , Model model) {
        DoctorResponseDto doctorResponseDto = doctorFacade.findById(id);
        model.addAttribute("doctor", doctorResponseDto);
        System.out.println("get doctor profile controller!");
        return "pages/doctor/profile";
    }

    @GetMapping("/profile/edit/{id}")
    public String profileEdit(@PathVariable Long id, Model model) {
        DoctorResponseDto doctorResponseDto = doctorFacade.findById(id);
        model.addAttribute("doctor", doctorResponseDto);
        System.out.println("get edit controller doctor");
        return "pages/doctor/profile_edit";
    }

    @PostMapping("/profile/edit/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute("doctor") DoctorRequestDto doctorRequestDto) {
        doctorFacade.update(doctorRequestDto, id);
        System.out.println("post controller edit doctor");
        return "redirect:/doctors/dashboard";
    }
}