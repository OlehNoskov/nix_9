package com.project.medicalanalize.web.controller.patient;

import com.project.medicalanalize.facade.*;
import com.project.medicalanalize.web.dto.request.CheckUpRequestDto;
import com.project.medicalanalize.web.dto.request.ConsultationRequestDto;
import com.project.medicalanalize.web.dto.request.TranscriptRequestDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/patient/order")
public class PatientCreateOrderController {

    private final TranscriptFacade transcriptFacade;
    private final ConsultationOrderFacade consultationOrderFacade;
    private final CheckUpFacade checkUpFacade;
    private final PatientFacade patientFacade;
    private final UserFacade userFacade;


    public PatientCreateOrderController(TranscriptFacade transcriptFacade,
                                        ConsultationOrderFacade consultationOrderFacade,
                                        CheckUpFacade checkUpFacade,
                                        PatientFacade patientFacade,
                                        UserFacade userFacade) {
        this.transcriptFacade = transcriptFacade;
        this.consultationOrderFacade = consultationOrderFacade;
        this.checkUpFacade = checkUpFacade;
        this.patientFacade = patientFacade;
        this.userFacade = userFacade;
    }

    @GetMapping("/new/transcript")
    public String newTranscript(Model model) {
//        PatientResponseDto patientResponseDto = patientFacade.findById(id);
//        model.addAttribute("patient", patientResponseDto);
        model.addAttribute("transcript", new TranscriptRequestDto());
        return "pages/patient/order/transcript";
    }

    @PostMapping("/new/transcript")
    public String createNewTranscript(@ModelAttribute("transcript") TranscriptRequestDto transcriptRequestDto) {
        transcriptFacade.create(transcriptRequestDto);
        return "redirect:/patients/dashboard";
    }

//    @PostMapping("/new/transcript")
//    public String newTranscriptPayment(Model model) {
//        return "redirect:pages/patient/new/order/payment";
//    }

    @GetMapping("/new/check-up")
    public String newCheckUp(Model model) {
        model.addAttribute("check_up", new CheckUpRequestDto());
        return "pages/patient/order/check_up";
    }

    @PostMapping("/new/check-up")
    public String createNewCheckUp(@ModelAttribute("check_up") CheckUpRequestDto checkUpRequestDto) {
        checkUpFacade.create(checkUpRequestDto);
        return "redirect:/patients/dashboard";
    }

    @GetMapping("/new/comprehensive")
    public String newComprehensive(Model model) {
        model.addAttribute("consultation", new ConsultationRequestDto());
        return "pages/patient/order/comprehensive";
    }

    @PostMapping("/new/comprehensive")
    public String createConsultation(@ModelAttribute("consultation") ConsultationRequestDto consultationRequestDto) {
        consultationOrderFacade.create(consultationRequestDto);
        return "redirect:/patients/dashboard";
    }

    @GetMapping("/new")
    public String newOrder(Model model, WebRequest webRequest) {
        return "pages/patient/order/new_order";
    }

    @GetMapping("/all")
    public String allOrders(Model model, WebRequest webRequest) {
        return "pages/patient/order/my_orders_all";
    }

    @GetMapping("/success/all")
    public String allSuccessOrders(Model model, WebRequest webRequest) {
        return "pages/patient/order/success_all_orders";
    }

    @GetMapping("/review/all")
    public String allReviewOrders(Model model, WebRequest webRequest) {
        return "pages/patient/order/review_all_orders";
    }
}