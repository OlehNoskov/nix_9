package com.project.medicalanalize.web.controller.doctor;

import com.project.medicalanalize.facade.DoctorFacade;
import com.project.medicalanalize.facade.OrderFacade;
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
    private final OrderFacade orderFacade;

    public DoctorController(DoctorFacade doctorFacade, UserFacade userFacade, OrderFacade orderFacade) {
        this.doctorFacade = doctorFacade;
        this.userFacade = userFacade;
        this.orderFacade = orderFacade;
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
    public String updatePatient(@PathVariable Long id, @ModelAttribute("doctor") DoctorRequestDto doctorRequestDto) throws ParseException {
        doctorFacade.update(doctorRequestDto, id);
        return "redirect:/doctors/dashboard";
    }

    @GetMapping("/orders/{doctorId}/{orderId}")
    public String addOrder(@PathVariable Long orderId, @PathVariable Long doctorId, Model model) {
        doctorFacade.addOrder(doctorId, orderId);
        DoctorResponseDto doctor = orderFacade.getDoctor(orderId);
        model.addAttribute("order", orderFacade.findById(orderId));
        model.addAttribute("doctor", doctor);
        return "pages/student/student_details";
    }
}