package com.project.medicalanalize.web.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient/feedback")
public class PatientFeedbackController {

    @GetMapping
    public String dashboard(Model model) {
        return "pages/patient/feedback/feedback_all";
    }

    @GetMapping("/new")
    public String redirectToNewFeedback(Model model) {
        return "pages/patient/feedback/feedback_new";
    }

//    @PostMapping("/new")
//    public String createNewFeedback(@ModelAttribute("feedback") FeedbackRequestDto feedbackRequestDto) {
//        feedbackFacade.create(feedbackRequestDto);
//        return "redirect:/patients/dashboard";
//    }
}
