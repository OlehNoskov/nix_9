package com.project.medicalanalize.web.controller.patient;

import com.project.medicalanalize.facade.*;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.web.dto.request.CheckUpRequestDto;
import com.project.medicalanalize.web.dto.request.ConsultationRequestDto;
import com.project.medicalanalize.web.dto.request.TranscriptRequestDto;
import com.project.medicalanalize.web.dto.response.CheckUpResponseDto;
import com.project.medicalanalize.web.dto.response.ConsultationResponseDto;
import com.project.medicalanalize.web.dto.response.PatientResponseDto;
import com.project.medicalanalize.web.dto.response.TranscriptResponseDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        if (user.getFirstName() == null && user.getLastName() == null) {
            return "redirect:/patients/profile/edit/" + user.getId();
        } else {
            model.addAttribute("patient", patientResponseDto);
            model.addAttribute("transcript", new TranscriptRequestDto());
            return "pages/patient/order/transcript";
        }
    }

    @PostMapping("/new/transcript")
    public String createNewTranscript(@ModelAttribute("orderId") TranscriptRequestDto transcriptRequestDto, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("orderId", transcriptFacade.createAndFind(transcriptRequestDto));
        return "redirect:/patient/order/transcript/payment";
    }

    @GetMapping("/transcript/payment")
    public String pagePaymentTranscript(Model model, @RequestParam Long orderId) {
        TranscriptResponseDto transcriptResponseDto = transcriptFacade.findById(orderId);
        model.addAttribute("transcript", transcriptResponseDto);
        return "pages/patient/payment";
    }

    @PostMapping("/transcript/payment")
    public String pagePaymentTranscriptPayment(@RequestParam Long orderId) {
        transcriptFacade.paymentStatus(orderId);
        return "redirect:/patients/dashboard";
    }

    @GetMapping("/new/check-up")
    public String newCheckUp(Model model) {
        User user = userFacade.getCurrentUser();
        PatientResponseDto patientResponseDto = patientFacade.findById(user.getId());
        if (user.getFirstName() == null && user.getLastName() == null) {
            return "redirect:/patients/profile/edit/" + user.getId();
        } else {
            model.addAttribute("patient", patientResponseDto);
            model.addAttribute("check_up", new CheckUpRequestDto());
            return "pages/patient/order/check_up";
        }
    }

    @PostMapping("/new/check-up")
    public String createNewCheckUp(@ModelAttribute("check_up") CheckUpRequestDto checkUpRequestDto, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("orderId", checkUpFacade.createAndFind(checkUpRequestDto));
        return "redirect:/patient/order/check-up/payment";
    }

    @GetMapping("/check-up/payment")
    public String pagePaymentCheckUp(Model model, @RequestParam Long orderId) {
        CheckUpResponseDto checkUpResponseDto = checkUpFacade.findById(orderId);
        model.addAttribute("check_up", checkUpResponseDto);
        return "pages/patient/payment_check_up";
    }

    @PostMapping("/check-up/payment")
    public String pagePaymentCheckUpPayment(@RequestParam Long orderId) {
        checkUpFacade.paymentStatus(orderId);
        return "redirect:/patients/dashboard";
    }

    @GetMapping("/new/comprehensive")
    public String newComprehensive(Model model) {
        User user = userFacade.getCurrentUser();
        PatientResponseDto patientResponseDto = patientFacade.findById(user.getId());
        if (user.getFirstName() == null && user.getLastName() == null) {
            return "redirect:/patients/profile/edit/" + user.getId();
        } else {
            model.addAttribute("patient", patientResponseDto);
            model.addAttribute("consultation", new ConsultationRequestDto());
            return "pages/patient/order/comprehensive";
        }
    }

    @PostMapping("/new/comprehensive")
    public String createConsultation(@ModelAttribute("consultation") ConsultationRequestDto consultationRequestDto, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("orderId", consultationOrderFacade.createAndFind(consultationRequestDto));
        return "redirect:/patient/order/consultation/payment";
    }

    @GetMapping("/consultation/payment")
    public String pagePaymentConsultation(Model model, @RequestParam Long orderId) {
        ConsultationResponseDto consultationResponseDto = consultationOrderFacade.findById(orderId);
        model.addAttribute("consultation", consultationResponseDto);
        return "pages/patient/payment_consultation";
    }

    @PostMapping("/consultation/payment")
    public String pagePaymentConsultationPayment(@RequestParam Long orderId) {
        consultationOrderFacade.paymentStatus(orderId);
        return "redirect:/patients/dashboard";
    }

    @GetMapping("/new")
    public String newOrder() {
        return "pages/patient/order/new_order";
    }

    @GetMapping("/success/all")
    public String allSuccessOrders() {
        return "pages/patient/order/success_all_orders";
    }
}