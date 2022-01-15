package com.project.medicalanalize.web.controller.patient;

import com.project.medicalanalize.facade.FeedbackFacade;
import com.project.medicalanalize.facade.PatientFacade;
import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.web.dto.request.FeedbackRequestDto;
import com.project.medicalanalize.web.dto.response.PatientResponseDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient/feedback")
public class PatientFeedbackController {

    private final FeedbackFacade feedbackFacade;
    private final PatientFacade patientFacade;
    private final UserFacade userFacade;

    public PatientFeedbackController(FeedbackFacade feedbackFacade, PatientFacade patientFacade, UserFacade userFacade) {
        this.feedbackFacade = feedbackFacade;
        this.patientFacade = patientFacade;
        this.userFacade = userFacade;
    }

    @GetMapping
    public String dashboard(Model model) {
        User user = userFacade.getCurrentUser();
        PatientResponseDto patientResponseDto = patientFacade.findById(user.getId());
        model.addAttribute("patient", patientResponseDto);
        return "pages/patient/feedback/feedback_all";
    }

    @GetMapping("/new")
    public String createNewFeedback(Model model) {
        model.addAttribute("feedback", new FeedbackRequestDto());
        return "pages/patient/feedback/feedback_new";
    }

    @PostMapping("/new")
    public String createNewFeedback(@ModelAttribute("feedback") FeedbackRequestDto feedbackRequestDto) {
        feedbackFacade.create(feedbackRequestDto);
        return "redirect:/patients/dashboard";
    }
}