package com.project.medicalanalize.web.controller.admin;

import com.project.medicalanalize.facade.TranscriptFacade;
import com.project.medicalanalize.web.controller.AbstractController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    private final TranscriptFacade transcriptFacade;

    public AdminController(TranscriptFacade transcriptFacade) {
        this.transcriptFacade = transcriptFacade;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
//        TranscriptResponseDto transcriptResponseDto = transcriptFacade.findAllTranscriptAdmin();
//        model.addAttribute("transcript", transcriptResponseDto);
        return "pages/admin/dashboard";
    }
}