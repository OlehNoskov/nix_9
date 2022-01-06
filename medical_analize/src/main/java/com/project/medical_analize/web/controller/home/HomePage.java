package com.project.medical_analize.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class HomePage {

    @GetMapping()
    public String dashboard(Model model) {
        System.out.println("i am here home page controller! ");
        return "pages/open/open";
    }
}