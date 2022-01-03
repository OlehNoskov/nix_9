package com.project.medical_analize.web.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient/new/order/")
public class PaymentController {

    @GetMapping("/payment")
    public String redirectToNew(Model model) {
        return "pages/patient/payment";
    }
}