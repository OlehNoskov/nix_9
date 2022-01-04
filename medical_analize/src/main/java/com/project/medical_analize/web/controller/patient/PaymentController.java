package com.project.medical_analize.web.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient/new/order/")
public class PaymentController {

    @GetMapping("/payment")
    public String pagePayment(Model model) {
        return "pages/patient/payment";
    }

    @PostMapping("/payment")
    public String pagePaymentRedirect(Model model) {
        return "redirect:/patient/new order/payment/successful";
    }

    @GetMapping("/payment/successful")
    public String returnToDashboard(Model model){
        return "pages/patient/successful_payment";
    }
}