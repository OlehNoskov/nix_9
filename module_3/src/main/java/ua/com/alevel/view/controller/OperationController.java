package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ua.com.alevel.facade.OperationFacade;
import ua.com.alevel.view.dto.request.OperationRequestDto;

import java.util.Map;

@Controller
@RequestMapping("/operations")
public class OperationController extends AbstractController {

    private final OperationFacade operationFacade;

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#"),
            new HeaderName("Назначение"),
            new HeaderName("Доход / Расход"),
            new HeaderName("Удалить")
    };

    public OperationController(OperationFacade operationFacade) {
        this.operationFacade = operationFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        initDataTable(columnNames, model);
        model.addAttribute("operations", operationFacade.findAll());
        model.addAttribute("createNew", "/operations/new");
        model.addAttribute("cardHeader", "Все операции");
        return "pages/operation/operation_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/operations", model);
    }

    @GetMapping("/new")
    public String redirectToNewOperationPage(Model model) {
        model.addAttribute("operation", new OperationRequestDto());
        return "pages/operation/operation_new";
    }


    @PostMapping("/create")
    public String create(@ModelAttribute("transaction") OperationRequestDto dto) {
        System.out.println("from - " + dto.toString());
        operationFacade.create(dto);
        return "redirect:/operations";
    }

    @GetMapping("/delete/{id}")
    public String deleteDyId(@PathVariable Long id, Model model) {
        operationFacade.delete(id);
        return "redirect:/operations";
    }
}