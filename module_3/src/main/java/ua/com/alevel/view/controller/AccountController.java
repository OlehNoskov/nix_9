package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.view.dto.request.AccountStatementRequestDto;
import ua.com.alevel.view.dto.response.TransactionResponseDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/accounts")
public class AccountController extends AbstractController {

    private final AccountFacade accountFacade;
    private final TransactionFacade transactionFacade;

    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#"),
            new HeaderName("Number check"),
            new HeaderName("Balance")
    };

    public AccountController(AccountFacade accountFacade, TransactionFacade transactionFacade) {
        this.accountFacade = accountFacade;
        this.transactionFacade = transactionFacade;
    }


    @GetMapping
    public String findAll(Model model, WebRequest request) {
        initDataTable(columnNames, model);
        model.addAttribute("accounts", accountFacade.findAll());
        model.addAttribute("cardHeader", "All check");
        return "pages/account/account_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/accounts", model);
    }

    @GetMapping("/details/{id}")
    public String findDyId(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountFacade.findById(id));

        List<TransactionResponseDto> transactionResponseDtoList = transactionFacade.findTransactionsByAccountId(id);
        List<Date> transactionDates = new ArrayList<>();

        for (int i = 0; i < transactionResponseDtoList.size(); i++) {
            transactionDates.add(transactionResponseDtoList.get(i).getCreated());
        }

        model.addAttribute("accountTransactions", transactionResponseDtoList);
        model.addAttribute("accountDataList", transactionDates);
        model.addAttribute("accountStatement", new AccountStatementRequestDto(id));
        return "pages/account/account_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteDyId(@PathVariable Long id, Model model) {
        accountFacade.delete(id);
        return "redirect:/accounts";
    }

    @GetMapping("/create/{id}")
    public String createByUser(@PathVariable Long id, Model model) {
        accountFacade.create(id);
        return "redirect:/users/details/" + id;
    }

    @PostMapping("/getAnExtract")
    public ResponseEntity<ByteArrayResource> getAnExtract(@ModelAttribute("accountStatement") AccountStatementRequestDto dto) {
        File file = new File(accountFacade.getAccountStatementFileForDownload(dto));
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=account_" + dto.getId() + "_statement.csv");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}