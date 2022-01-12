package com.project.medicalanalize.web.controller.patient;

import com.project.medicalanalize.facade.FeedbackFacade;
import com.project.medicalanalize.web.dto.request.FeedbackRequestDto;

import com.project.medicalanalize.web.dto.request.TranscriptRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient/feedback")
public class PatientFeedbackController {

    private final FeedbackFacade feedbackFacade;

    public PatientFeedbackController(FeedbackFacade feedbackFacade) {
        this.feedbackFacade = feedbackFacade;
    }

    @GetMapping
    public String dashboard(Model model) {
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