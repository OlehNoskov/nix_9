package com.project.medicalanalize.web.controller.admin;

import com.project.medicalanalize.facade.TranscriptFacade;
import com.project.medicalanalize.web.controller.AbstractController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    private final TranscriptFacade transcriptFacade;

    public AdminController(TranscriptFacade transcriptFacade) {
        this.transcriptFacade = transcriptFacade;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, WebRequest webRequest) {
        return "pages/admin/dashboard";
    }
}