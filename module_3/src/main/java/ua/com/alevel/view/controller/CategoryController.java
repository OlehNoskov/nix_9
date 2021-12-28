package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {
//    private final AppContext context = AppContext.getInstance();

    @GetMapping
    public String findAll(Model model) {
//        model.addAttribute("categories", context.findAllCategories());
        return "pages/category/category_all";
    }
}