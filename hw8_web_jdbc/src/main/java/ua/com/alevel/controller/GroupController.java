package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.group.GroupRequestDto;
import ua.com.alevel.dto.group.GroupResponseDto;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.type.GroupType;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class GroupController {
    private final GroupFacade groupFacade;

    public GroupController(GroupFacade groupFacade) {
        this.groupFacade = groupFacade;
    }


    @PostMapping("/new")
    public String createNewGroup(@ModelAttribute("department") GroupRequestDto groupRequestDto) {
        groupFacade.create(groupRequestDto);
        return "redirect:/departments";
    }

    @GetMapping
    public String findAll(Model model) {
        List<GroupResponseDto> groups = groupFacade.findAll();
        model.addAttribute("departments", groups);
        return "pages/department/department_all";
    }

    @GetMapping("/new")
    public String redirectToNewGroupPage(Model model) {
        model.addAttribute("department", new GroupRequestDto());
        model.addAttribute("types", GroupType.values());
        return "pages/department/department_new";
    }


    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("department", groupFacade.findById(id));
        return "pages/department/department_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        groupFacade.delete(id);
        return "redirect:/departments";
    }
}