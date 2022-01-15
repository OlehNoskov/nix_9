package com.project.medicalanalize.web.controller.doctor;

import com.project.medicalanalize.facade.DoctorFacade;
import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.web.dto.request.DoctorRequestDto;
import com.project.medicalanalize.web.dto.response.DoctorResponseDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorFacade doctorFacade;
    private final UserFacade userFacade;

    public DoctorController(DoctorFacade doctorFacade, UserFacade userFacade) {
        this.doctorFacade = doctorFacade;
        this.userFacade = userFacade;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "pages/doctor/dashboard";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        User user = userFacade.getCurrentUser();
        DoctorResponseDto doctorResponseDto = doctorFacade.findById(user.getId());
        model.addAttribute("doctor", doctorResponseDto);
        return "pages/doctor/profile";
    }

    @GetMapping("/profile/edit/{id}")
    public String profileEdit(@PathVariable Long id, Model model) {
        DoctorResponseDto doctorResponseDto = doctorFacade.findById(id);
        model.addAttribute("doctor", doctorResponseDto);
        return "pages/doctor/profile_edit";
    }

    @PostMapping("/profile/edit/{id}")
    public String updateDoctor(@PathVariable Long id, @ModelAttribute("doctor") DoctorRequestDto doctorRequestDto) throws ParseException {
        doctorFacade.update(doctorRequestDto, id);
        return "redirect:/doctors/dashboard";
    }
}