package com.project.medicalanalize.web.controller.patient;

import com.project.medicalanalize.facade.FeedbackFacade;
import com.project.medicalanalize.web.dto.request.FeedbackRequestDto;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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





//    private final FeedbackFacade feedbackFacade;
//    private final PatientFacade patientFacade;
//
//    public PatientFeedbackController(FeedbackFacade feedbackFacade, PatientFacade patientFacade) {
//        this.feedbackFacade = feedbackFacade;
//        this.patientFacade = patientFacade;
//    }
//
//    @GetMapping
//    public String dashboard(Model model) {
//        return "pages/patient/feedback/feedback_all";
//    }
//
//    @GetMapping("/new")
//    public String createNewFeedback(Model model, @RequestParam Long feedbackId, WebRequest request) {
//        FeedbackResponseDto dto = feedbackFacade.findById(feedbackId);
////        List<PatientResponseDto> patients = patientFacade.findAll(request).getItems();
////        LinkRequestDto linkRequestDto = new LinkRequestDto();
////        linkRequestDto.setFeedbackId(feedbackId);
//        model.addAttribute("feedback", dto);
////        model.addAttribute("link", linkRequestDto);
////        model.addAttribute("patient", patients);
////        model.addAttribute("action", "/patient/feedback/new");
//        return "pages/patient/feedback/feedback_new";
//    }
//
//    @PostMapping("/new")
//    public String createNewFeedback(@ModelAttribute("feedback") FeedbackRequestDto feedbackRequestDto) {
//        feedbackFacade.create(feedbackRequestDto);
//        return "redirect:/patients/dashboard";
//    }