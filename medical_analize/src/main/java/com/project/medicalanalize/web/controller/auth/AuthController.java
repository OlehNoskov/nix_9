package com.project.medicalanalize.web.controller.auth;

import com.project.medicalanalize.config.security.SecurityService;
import com.project.medicalanalize.facade.AuthValidatorFacade;
import com.project.medicalanalize.facade.RegistrationFacade;
import com.project.medicalanalize.persistence.repository.type.RoleType;
import com.project.medicalanalize.util.SecurityUtil;
import com.project.medicalanalize.web.controller.AbstractController;
import com.project.medicalanalize.web.dto.request.register.AuthDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController extends AbstractController {

    private final RegistrationFacade registrationFacade;
    private final AuthValidatorFacade authValidatorFacade;
    private final SecurityService securityService;

    public AuthController(
            RegistrationFacade registrationFacade,
            AuthValidatorFacade authValidatorFacade,
            SecurityService securityService) {
        this.registrationFacade = registrationFacade;
        this.authValidatorFacade = authValidatorFacade;
        this.securityService = securityService;
    }

    @GetMapping("/")
    public String main(Model model) {
        return redirectProcess(model);
    }

    @GetMapping("/open")
    public String open(Model model) {
        return "pages/open/open";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        showMessage(model, false);
        boolean authenticated = securityService.isAuthenticated();
        if (authenticated) {
            if (SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name())) {
                return "redirect:/admin/dashboard";
            }
            if (SecurityUtil.hasRole(RoleType.ROLE_DOCTOR.name())) {
                return "redirect:/doctors/dashboard";
            }
            if (SecurityUtil.hasRole(RoleType.ROLE_PATIENT.name())) {
                return "redirect:/patients/dashboard";

            } else {
                return "redirect:/main";
            }
        }
        if (error != null) {
            showError(model, "Your email and password is invalid.");
        }
        if (logout != null) {
            showInfo(model, "You have been logged out successfully.");
        }
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return redirectProcess(model);
        }
        model.addAttribute("authForm", new AuthDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("authForm") AuthDto authForm, BindingResult bindingResult, Model model) {
        showMessage(model, false);
        authValidatorFacade.validate(authForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        registrationFacade.registration(authForm);
        securityService.autoLogin(authForm.getEmail(), authForm.getPasswordConfirm());
        return redirectProcess(model);
    }

    private String redirectProcess(Model model) {
        showMessage(model, false);
        if (SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name())) {
            return "redirect:/admin/dashboard";
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_DOCTOR.name())) {
            return "redirect:/doctors/dashboard";
        }
        if (SecurityUtil.hasRole(RoleType.ROLE_PATIENT.name())) {
            return "redirect:/patients/dashboard";
        }
        if (!SecurityUtil.hasRole(RoleType.ROLE_PATIENT.name())
                & !SecurityUtil.hasRole(RoleType.ROLE_DOCTOR.name())
                & !SecurityUtil.hasRole(RoleType.ROLE_ADMIN.name())) {
            return "redirect:/main";
        }
        return "redirect:/login";
    }
}