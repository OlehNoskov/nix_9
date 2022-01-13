package com.project.medicalanalize.web.controller.admin;

import com.project.medicalanalize.web.controller.AbstractController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "pages/admin/dashboard";
    }
}