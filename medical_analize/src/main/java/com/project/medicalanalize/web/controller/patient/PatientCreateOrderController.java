package com.project.medicalanalize.web.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;


@Controller
@RequestMapping("/patient/order")
public class PatientCreateOrderController {

    @GetMapping("/new/transcript")
    public String transcript(Model model) {
        return "pages/patient/order/transcript";
    }

    @PostMapping("/new/transcript")
    public String newTranscriptPayment(Model model) {
        return "redirect:pages/patient/new/order/payment";
    }

    @GetMapping("/new/check-up")
    public String check_up(Model model) {
        return "pages/patient/order/check_up";
    }

    @GetMapping("/new/comprehensive")
    public String comprehensive(Model model) {
        return "pages/patient/order/comprehensive";
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