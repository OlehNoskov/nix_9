package com.project.medicalanalize.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrdersController {

    @GetMapping()
    public String dashboard(Model model) {
        return "pages/admin/admin_orders_all";
    }
}