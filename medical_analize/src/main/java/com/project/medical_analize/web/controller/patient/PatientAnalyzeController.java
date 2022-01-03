package com.project.medical_analize.web.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient/analyzes")
public class PatientAnalyzeController {

    @GetMapping("/all/success")
    public String success(Model model) {
        return "pages/patient/analyzes/analyzes_all";
    }

    @GetMapping("/all/review")
    public String review(Model model) {
        return "pages/patient/analyzes/analyzes_all";
    }

    @GetMapping("/all/temp")
    public String temp(Model model) {
        return "pages/patient/analyzes/analyzes_all";
    }

//    @GetMapping("/new")
//    public String redirectToNew(Model model) {
//        return "pages/patient/analyzes/analyzes_new";
//    }

    @GetMapping("/all/examples")
    public String example(Model model){
        return "pages/patient/analyzes/example";
    }

    @GetMapping("/all/examples/transcript")
    public String exampleTranscript(Model model){
        return "pages/patient/analyzes/example_transcript";
    }
}