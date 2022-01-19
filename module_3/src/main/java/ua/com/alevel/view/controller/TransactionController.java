package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ua.com.alevel.facade.OperationFacade;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.view.dto.request.TransactionRequestDto;

import java.util.Map;

@Controller
@RequestMapping("/transactions")
public class TransactionController extends AbstractController {

    private final TransactionFacade transactionFacade;
    private final OperationFacade operationFacade;

    public TransactionController(TransactionFacade transactionFacade, OperationFacade operationFacade) {
        this.transactionFacade = transactionFacade;
        this.operationFacade = operationFacade;
    }

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#"),
            new HeaderName("User"),
            new HeaderName("Check number"),
            new HeaderName("Event"),
            new HeaderName("Sum"),
    };

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        initDataTable(columnNames, model);
        model.addAttribute("transactions", transactionFacade.findAll());
        model.addAttribute("cardHeader", "All transactions");
        return "pages/transaction/transaction_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/transactions", model);
    }

    @GetMapping("/new/{id}")
    public String redirectToNewTransactionPage(@PathVariable Long id, Model model) {
        model.addAttribute("transaction", new TransactionRequestDto());
        model.addAttribute("accountId", id);
        model.addAttribute("operations", operationFacade.findAll());
        return "pages/transaction/transaction_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("transaction") TransactionRequestDto dto, @RequestParam("selectedOperation") String selectedOperation) {
        try {
            String [] selected = selectedOperation.split(",");
            long operationId = Long.valueOf(selected[0]);
            transactionFacade.create(dto, (Long)operationId);
            return "redirect:/accounts/details/" + dto.getAccountId();
        }catch (NumberFormatException ex) {
            throw new RuntimeException("You have not selected an operation!");
        }
    }
}