package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.view.dto.request.UserRequestDto;

import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController extends AbstractController {

    private final UserFacade userFacade;
    private final AccountFacade accountFacade;
    private final TransactionFacade transactionFacade;

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#"),
            new HeaderName("Имя"),
            new HeaderName("Фамилия"),
            new HeaderName("Возраст"),
            new HeaderName("Инфо"),
            new HeaderName("Удалить")
    };

    public UserController(UserFacade userFacade, AccountFacade accountFacade, TransactionFacade transactionFacade) {
        this.userFacade = userFacade;
        this.accountFacade = accountFacade;
        this.transactionFacade = transactionFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        initDataTable(columnNames, model);
        model.addAttribute("users", userFacade.findAll());
        model.addAttribute("createNew", "/users/new");
        model.addAttribute("cardHeader", "Все пользователи");
        return "pages/user/user_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/users", model);
    }

    @GetMapping("/details/{id}")
    public String findDyId(@PathVariable Long id, Model model) {
        model.addAttribute("user", userFacade.findById(id));
        model.addAttribute("accounts", accountFacade.findByUserId(id));
        return "pages/user/user_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteDyId(@PathVariable Long id, Model model) {
        userFacade.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String redirectToNewUserPage(Model model) {
        model.addAttribute("user", new UserRequestDto());
        return "pages/user/user_new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("user") UserRequestDto dto) {
        userFacade.create(dto);
        return "redirect:/users";
    }

    @GetMapping("/details/update/{id}")
    public String updateById(@PathVariable Long id, Model model) {
        model.addAttribute("user", userFacade.findById(id));
        return "pages/user/user_update";
    }

    @PostMapping("/details/update")
    public String update(@ModelAttribute("product") UserRequestDto dto) {
        userFacade.update(dto);
        return "redirect:/users";
    }

    @GetMapping("/showTransactions/{id}")
    public String showTransactions(@PathVariable Long id, Model model) {
        model.addAttribute("userTransactions", transactionFacade.findTransactionsByUserId(id));
        return "pages/user/user_transactions";
    }
}