package com.project.medicalanalize.web.controller.patient;

import com.project.medicalanalize.facade.*;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.web.dto.request.CheckUpRequestDto;
import com.project.medicalanalize.web.dto.request.ConsultationRequestDto;
import com.project.medicalanalize.web.dto.request.TranscriptRequestDto;
import com.project.medicalanalize.web.dto.response.PatientResponseDto;

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
    private final UserFacade userFacade;
    private final PatientFacade patientFacade;


    public PatientCreateOrderController(TranscriptFacade transcriptFacade,
                                        ConsultationOrderFacade consultationOrderFacade,
                                        CheckUpFacade checkUpFacade,
                                        UserFacade userFacade, PatientFacade patientFacade) {
        this.transcriptFacade = transcriptFacade;
        this.consultationOrderFacade = consultationOrderFacade;
        this.checkUpFacade = checkUpFacade;
        this.userFacade = userFacade;
        this.patientFacade = patientFacade;
    }

    @GetMapping("/new/transcript")
    public String newTranscript(Model model) {
        User user = userFacade.getCurrentUser();
        PatientResponseDto patientResponseDto = patientFacade.findById(user.getId());
        model.addAttribute("patient", patientResponseDto);
        model.addAttribute("transcript", new TranscriptRequestDto());
        return "pages/patient/order/transcript";
    }

    @PostMapping("/new/transcript")
    public String createNewTranscript(@ModelAttribute("transcript") TranscriptRequestDto transcriptRequestDto) {
        transcriptFacade.create(transcriptRequestDto);
        return "redirect:/patient/order/transcript/payment";
    }

    @GetMapping("/transcript/payment")
    public String pagePaymentTranscript(Model model) {
        model.addAttribute("transcript", new TranscriptRequestDto());
        return "pages/patient/payment";
    }

    @GetMapping("/check-up/payment")
    public String pagePaymentCheckUp(Model model) {
        return "pages/patient/payment";
    }

    @GetMapping("/consultation/payment")
    public String pagePaymentConsultation(Model model) {
        return "pages/patient/payment";
    }


    @GetMapping("/new/check-up")
    public String newCheckUp(Model model) {
        model.addAttribute("check_up", new CheckUpRequestDto());
        return "pages/patient/order/check_up";
    }

    @PostMapping("/new/check-up")
    public String createNewCheckUp(@ModelAttribute("check_up") CheckUpRequestDto checkUpRequestDto) {
        checkUpFacade.create(checkUpRequestDto);
        return "redirect:/patient/order/check-up/payment";
    }

    @GetMapping("/new/comprehensive")
    public String newComprehensive(Model model) {
        model.addAttribute("consultation", new ConsultationRequestDto());
        return "pages/patient/order/comprehensive";
    }

    @PostMapping("/new/comprehensive")
    public String createConsultation(@ModelAttribute("consultation") ConsultationRequestDto consultationRequestDto) {
        consultationOrderFacade.create(consultationRequestDto);
        return "redirect:/patient/order/consultation/payment";
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
}