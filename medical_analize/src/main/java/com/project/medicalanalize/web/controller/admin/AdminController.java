package com.project.medicalanalize.web.controller.admin;

import com.project.medicalanalize.facade.TranscriptFacade;
import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.repository.order.CheckUpRepository;
import com.project.medicalanalize.persistence.repository.order.ConsultationOrderRepository;
import com.project.medicalanalize.persistence.repository.order.TranscriptRepository;
import com.project.medicalanalize.persistence.repository.user.DoctorRepository;
import com.project.medicalanalize.persistence.repository.user.PatientRepository;
import com.project.medicalanalize.web.controller.AbstractController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final TranscriptRepository transcriptRepository;
    private final CheckUpRepository checkUpRepository;
    private final ConsultationOrderRepository consultationOrderRepository;

    public AdminController(TranscriptFacade transcriptFacade,
                           DoctorRepository doctorRepository,
                           PatientRepository patientRepository, TranscriptRepository transcriptRepository, CheckUpRepository checkUpRepository, ConsultationOrderRepository consultationOrderRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.transcriptRepository = transcriptRepository;
        this.checkUpRepository = checkUpRepository;
        this.consultationOrderRepository = consultationOrderRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, WebRequest webRequest) {
        Long countDoctors = doctorRepository.count();
        model.addAttribute("countDoctors", countDoctors);

        Long countPatients = patientRepository.count();
        model.addAttribute("countPatients", countPatients);

        Long allUsers = allUsers(countDoctors, countPatients);
        model.addAttribute("allUsers", allUsers);

        Long countSuccessTranscript = transcriptRepository.countSuccessTranscript();
        model.addAttribute("countSuccessTranscript", countSuccessTranscript);

        Long countReviewTranscript = transcriptRepository.countReviewTranscript();
        model.addAttribute("countReviewTranscript", countReviewTranscript);

        Long countSuccessCheckUp = checkUpRepository.countSuccessCheckUp();
        model.addAttribute("countSuccessCheckUp", countSuccessCheckUp);

        Long countReviewCheckUp = checkUpRepository.countReviewCheckUp();
        model.addAttribute("countReviewCheckUp", countReviewCheckUp);

        Long countSuccessConsultation = consultationOrderRepository.countSuccessConsultation();
        model.addAttribute("countSuccessConsultation", countSuccessConsultation);

        Long countReviewConsultation = consultationOrderRepository.countReviewConsultation();
        model.addAttribute("countReviewConsultation", countReviewConsultation);

        Long allSuccessOrders = allSuccessOrders(countSuccessTranscript, countSuccessCheckUp, countSuccessConsultation);
        model.addAttribute("allSuccessOrders", allSuccessOrders);

        Long allReviewsOrders = allReviewsOrders(countReviewTranscript, countReviewCheckUp, countReviewConsultation);
        model.addAttribute("allReviewsOrders", allReviewsOrders);

        Long allGeneralMoney = generalMoney(countSuccessTranscript, countSuccessCheckUp, countSuccessConsultation);
        model.addAttribute("allGeneralMoney", allGeneralMoney);

        Double disposableIncome = allGeneralMoney * 0.25;
        model.addAttribute("disposableIncome", disposableIncome);

        Long allOrders = allSuccessOrders(countSuccessTranscript, countSuccessCheckUp, countSuccessConsultation) +
                allReviewsOrders(countReviewTranscript, countReviewCheckUp, countReviewConsultation);
        model.addAttribute("allOrders", allOrders);

        Long countAllTranscript = transcriptRepository.countAllTranscript();
        model.addAttribute("allTranscript", countAllTranscript);

        Long countAllCheckUp = checkUpRepository.countAllCheckUp();
        model.addAttribute("allCheckUp", countAllCheckUp);

        Long countAllConsultation = consultationOrderRepository.countAllConsultation();
        model.addAttribute("allConsultation", countAllConsultation);
        return "pages/admin/dashboard";
    }

    private Long allUsers(Long countDoctors, Long countPatients) {
        return countDoctors + countPatients;
    }

    private Long allSuccessOrders(Long countSuccessTranscript, Long countSuccessCheckUp, Long countSuccessConsultation) {
        return countSuccessTranscript + countSuccessCheckUp + countSuccessConsultation;
    }

    private Long allReviewsOrders(Long countReviewTranscript, Long countReviewCheckUp, Long countReviewConsultation) {
        return countReviewTranscript + countReviewCheckUp + countReviewConsultation;
    }

    private Long generalMoney(Long countSuccessTranscript, Long countSuccessCheckUp, Long countSuccessConsultation) {
        return (countSuccessTranscript * TranscriptOrder.getPrice()) + (countSuccessCheckUp * CheckUp.getPrice() +
                countSuccessConsultation * ConsultationOrder.getPrice());
    }
}