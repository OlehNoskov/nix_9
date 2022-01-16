//package com.project.medicalanalize.web.controller.patient;
//
//import com.project.medicalanalize.facade.*;
//import com.project.medicalanalize.persistence.entity.user.User;
//import com.project.medicalanalize.web.dto.request.CheckUpRequestDto;
//import com.project.medicalanalize.web.dto.request.ConsultationRequestDto;
//import com.project.medicalanalize.web.dto.request.TranscriptRequestDto;
//import com.project.medicalanalize.web.dto.response.PatientResponseDto;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/patient/completed/order")
//public class SuccessOrdersController {
//
//    private final TranscriptFacade transcriptFacade;
//    private final ConsultationOrderFacade consultationOrderFacade;
//    private final CheckUpFacade checkUpFacade;
//    private final PatientFacade patientFacade;
//    private final UserFacade userFacade;
//
//    public SuccessOrdersController(TranscriptFacade transcriptFacade,
//                                   ConsultationOrderFacade consultationOrderFacade,
//                                   CheckUpFacade checkUpFacade,
//                                   PatientFacade patientFacade, UserFacade userFacade) {
//        this.transcriptFacade = transcriptFacade;
//        this.consultationOrderFacade = consultationOrderFacade;
//        this.checkUpFacade = checkUpFacade;
//        this.patientFacade = patientFacade;
//        this.userFacade = userFacade;
//    }
//
//    @GetMapping("/transcript/details")
//    public String successTranscript(Model model) {
//        User user = userFacade.getCurrentUser();
//        PatientResponseDto patientResponseDto = patientFacade.findById(user.getId());
//        model.addAttribute("patient", patientResponseDto);
//        model.addAttribute("transcript", new TranscriptRequestDto());
//        return "success_order_transcript_all";
//    }
//
//    @GetMapping("/check_up/details")
//    public String successCheckUp(Model model) {
//        User user = userFacade.getCurrentUser();
//        PatientResponseDto patientResponseDto = patientFacade.findById(user.getId());
//        model.addAttribute("patient", patientResponseDto);
//        model.addAttribute("check_up", new CheckUpRequestDto());
//        return "success_order_check_up_all";
//    }
//
//    @GetMapping("/consultation/details")
//    public String successConsultation(Model model) {
//        User user = userFacade.getCurrentUser();
//        PatientResponseDto patientResponseDto = patientFacade.findById(user.getId());
//        model.addAttribute("patient", patientResponseDto);
//        model.addAttribute("consultation", new ConsultationRequestDto());
//        return "success_order_consultation_all";
//    }
//}
