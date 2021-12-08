package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;
import ua.com.alevel.facade.StudentFacade;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentFacade studentFacade;

    public StudentController(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<StudentResponseDto> response = studentFacade.findAll(request);
        System.out.println("response = " + response);
        model.addAttribute("pageData", response);
        return "pages/student/student_all";
    }

    @GetMapping("/new")
    public String redirectToNewStudentPage(Model model) {
        model.addAttribute("student", new StudentRequestDto());
        return "pages/student/student_new";
    }

    @PostMapping("/create")
    public String createNewStudent(@ModelAttribute("student") StudentRequestDto studentRequestDto) {
        studentFacade.create(studentRequestDto);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        studentFacade.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        StudentResponseDto studentResponseDto = studentFacade.findById(id);
        model.addAttribute("student", studentResponseDto);
        return "pages/student/student_details";
    }
}