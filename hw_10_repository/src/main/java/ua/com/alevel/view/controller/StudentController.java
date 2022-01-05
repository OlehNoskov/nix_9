package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController extends AbstractController {

    private final StudentFacade studentFacade;
    private final GroupFacade groupFacade;

    public StudentController(StudentFacade studentFacade, GroupFacade groupFacade) {
        this.studentFacade = studentFacade;
        this.groupFacade = groupFacade;
    }

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("first name", "firstname", "first_name"),
                new HeaderName("last name", "lastname", "first_name"),
                new HeaderName("age", "age", "age"),
                new HeaderName("created", "created", "created"),
                new HeaderName("details", null, null),
                new HeaderName("edit", null, null),
                new HeaderName("delete", null, null)
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<StudentResponseDto> response = studentFacade.findAll(request);
        initDataTable(response, getColumnTitles(), model);
        model.addAttribute("createUrl", "/students/all");
        model.addAttribute("cardHeader", "All Students");
        return "pages/student/student_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "students");
    }

    @GetMapping("/new")
    public String redirectToNewStudentPage(Model model) {
        model.addAttribute("student", new StudentRequestDto());
        return "pages/student/student_new";
    }

    @PostMapping("/new")
    public String createNewStudent(@ModelAttribute("student") StudentRequestDto studentRequestDto) {
        studentFacade.create(studentRequestDto);
        return "redirect:/students";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") StudentRequestDto studentRequestDto) {
        studentFacade.update(studentRequestDto, id);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        StudentResponseDto studentResponseDto = studentFacade.findById(id);
        model.addAttribute("student", studentResponseDto);
        return "pages/student/student_update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentFacade.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        StudentResponseDto studentResponseDto = studentFacade.findById(id);
        model.addAttribute("student", studentResponseDto);
        return "pages/student/student_details";
    }

    @GetMapping("/add/{id}")
    public String redirectToAddPatientPage(@PathVariable Long id, Model model, WebRequest request) {
        List<StudentResponseDto> students = studentFacade.findAll();
        model.addAttribute("students", students);
        model.addAttribute("group", groupFacade.findById(id));
        return "pages/student/student_add";
    }

    @GetMapping("/group/{studentId}/{groupId}")
    public String addStudent (@PathVariable Long studentId, @PathVariable Long groupId, Model model) {
        groupFacade.addStudent(groupId, studentId);
        List<StudentResponseDto> students = groupFacade.getStudents(groupId);
        model.addAttribute("group", groupFacade.findById(groupId));
        model.addAttribute("students", students);
        return "pages/group/group_details";
    }

    @GetMapping("/delete/group/{studentId}/{groupId}")
    public String deleteStudentFromGroup(@PathVariable Long studentId, @PathVariable Long groupId, Model model) {
        groupFacade.removeStudent(groupId,studentId);
        List<StudentResponseDto> students = groupFacade.getStudents(groupId);
        model.addAttribute("group", groupFacade.findById(groupId));
        model.addAttribute("students", students);
        return "pages/student/students_group";
    }
}