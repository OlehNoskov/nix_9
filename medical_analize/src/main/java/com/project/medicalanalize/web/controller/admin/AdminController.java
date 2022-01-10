package com.project.medicalanalize.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/doctors")
    public String allDoctors() {
        return "pages/admin/admin_doctors_all";
    }

    @GetMapping("/patients")
    public String allPatients() {
        return "pages/admin/admin_patients_all";
    }

    @GetMapping("/orders")
    public String allOrders() {
        return "pages/admin/admin_orders_all";
    }
}