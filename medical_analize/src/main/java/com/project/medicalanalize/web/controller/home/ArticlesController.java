package com.project.medicalanalize.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/articles/article")
public class ArticlesController {

    @GetMapping("/diagnostics_of_the_gastritis")
    public String gastritis(Model model) {
        return "pages/open/articles/gastritis";
    }

    @GetMapping("/thyroid_diagnostics")
    public String gastritis1(Model model) {
        return "pages/open/articles/thyroid";
    }

    @GetMapping("/mycoplasma")
    public String gastritis2(Model model) {
        return "pages/open/articles/mycoplasma";
    }

    @GetMapping("/lamblia_diagnostic_methods")
    public String gastritis3(Model model) {
        return "pages/open/articles/lamblia";
    }

    @GetMapping("/myocardial_infarction")
    public String gastritis4(Model model) {
        return "pages/open/articles/myocardial_infarction";
    }

    @GetMapping("/vitamin_d")
    public String gastritis5(Model model) {
        return "pages/open/articles/vitamin_d";
    }

    @GetMapping("/d_dimer")
    public String gastritis6(Model model) {
        return "pages/open/articles/d_dimer";
    }

    @GetMapping("/immunoconflict")
    public String gastritis7(Model model) {
        return "pages/open/articles/immunoconflict";
    }

    @GetMapping("/coprogram")
    public String gastritis8(Model model) {
        return "pages/open/articles/coprogram";
    }
}