package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.facade.StudentFacade;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentFacade studentFacade;

    public StudentController(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    @GetMapping("/groups/{id}")
    public String findAllStudentsByGroupId(Model model, @PathVariable Long id) {
        List<StudentResponseDto> students = studentFacade.findAllByGroupId(id);
        model.addAttribute("students", students);
        return "pages/student/student_all";
    }

    @GetMapping("/new/{groupId}")
    public String redirectToNewEmployeePage(@PathVariable Long groupId, Model model) {
        System.out.println("groupId = " + groupId);
        StudentRequestDto studentRequestDto = new StudentRequestDto();
        studentRequestDto.setGroupId(groupId);
        model.addAttribute("student", studentRequestDto);
        model.addAttribute("groupId", groupId);
        return "pages/student/student_new";
    }

    @PostMapping("/new")
    public String createNewEmployee(@ModelAttribute("student") StudentRequestDto studentRequestDto) {
        studentFacade.create(studentRequestDto);
        return "redirect:/students";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentFacade.findById(id));
        return "pages/student/student_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        studentFacade.delete(id);
        return "redirect:/students";
    }

    @GetMapping
    public String findAll(Model model) {
        List<StudentResponseDto> students = studentFacade.findAll();
        model.addAttribute("students", students);
        return "pages/student/student_all";
    }
}