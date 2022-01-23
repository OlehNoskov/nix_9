package com.project.medicalanalize.web.controller.doctor;

import com.project.medicalanalize.facade.DoctorFacade;
import com.project.medicalanalize.facade.TranscriptFacade;
import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.persistence.repository.order.CheckUpRepository;
import com.project.medicalanalize.persistence.repository.order.ConsultationOrderRepository;
import com.project.medicalanalize.persistence.repository.order.TranscriptRepository;
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
    private final TranscriptRepository transcriptRepository;
    private final CheckUpRepository checkUpRepository;
    private final ConsultationOrderRepository consultationOrderRepository;

    public DoctorController(DoctorFacade doctorFacade, UserFacade userFacade,
                            TranscriptFacade transcriptFacade, TranscriptRepository transcriptRepository,
                            CheckUpRepository checkUpRepository, ConsultationOrderRepository consultationOrderRepository) {
        this.doctorFacade = doctorFacade;
        this.userFacade = userFacade;
        this.transcriptRepository = transcriptRepository;
        this.checkUpRepository = checkUpRepository;
        this.consultationOrderRepository = consultationOrderRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        User user = userFacade.getCurrentUser();
        DoctorResponseDto doctorResponseDto = doctorFacade.findById(user.getId());
        Integer size = doctorResponseDto.getOrders().size();
        model.addAttribute("size", size);

        Long sizeTranscript = transcriptRepository.countSuccessDoctorTranscript(user.getId());
        model.addAttribute("countTranscript", sizeTranscript);

        Long sizeCheckUp = checkUpRepository.countSuccessDoctorCheckUp(user.getId());
        model.addAttribute("countCheckUp", sizeCheckUp);

        Long sizeConsultation = consultationOrderRepository.countSuccessDoctorConsultation(user.getId());
        model.addAttribute("countConsultation", sizeConsultation);

        Double salaryDoctor = salaryDoctor(sizeTranscript, sizeCheckUp, sizeConsultation);
        model.addAttribute("salaryDoctor", salaryDoctor);
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

    Double salaryDoctor(Long countTranscript, Long countCheckUp, Long countConsultation) {
        return (countTranscript * TranscriptOrder.getPrice()) +
                (countCheckUp * CheckUp.getPrice()) +
                (countConsultation * ConsultationOrder.getPrice() * 0.75);
    }
}