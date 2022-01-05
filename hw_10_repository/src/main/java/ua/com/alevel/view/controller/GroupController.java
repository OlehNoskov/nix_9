package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/groups")
public class GroupController extends AbstractController {

    private final GroupFacade groupFacade;
    private final StudentFacade studentFacade;

    public GroupController(GroupFacade groupFacade, StudentFacade studentFacade) {
        this.groupFacade = groupFacade;
        this.studentFacade = studentFacade;
    }

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("name", "name", "name"),
                new HeaderName("details", null, null),
                new HeaderName("edit", null, null),
                new HeaderName("delete", null, null)
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnTitles = getColumnTitles();
        PageData<GroupResponseDto> response = groupFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnTitles, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/groups/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Groups");
        return "pages/group/group_all";

    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/groups", model);
    }

    @GetMapping("/new")
    public String redirectToNewGroupPage(Model model) {
        model.addAttribute("group", new GroupRequestDto());
        return "pages/group/group_new";
    }

    @PostMapping("/new")
    public String createNewGroup(@ModelAttribute("group") GroupRequestDto dto) {
        groupFacade.create(dto);
        return "redirect:/groups";
    }

    @PostMapping("/update/{id}")
    public String updateGroup(@PathVariable Long id, @ModelAttribute("group") GroupRequestDto groupRequestDto) {
        groupFacade.update(groupRequestDto, id);
        return "redirect:/groups";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        GroupResponseDto groupResponseDto = groupFacade.findById(id);
        model.addAttribute("group", groupResponseDto);
        return "pages/group/group_update";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        GroupResponseDto groupResponseDto = groupFacade.findById(id);
        model.addAttribute("group", groupResponseDto);
        return "pages/group/group_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        List<StudentResponseDto> students = groupFacade.getStudents(id);
        for (StudentResponseDto student : students) {
            groupFacade.removeStudent(id, student.getId());
        }
        groupFacade.delete(id);
        return "redirect:/groups";
    }

    @GetMapping("/group/{groupId}/{studentId}")
    public String addStudent(@PathVariable Long studentId, @PathVariable Long groupId, Model model) {
        groupFacade.addStudent(groupId, studentId);
        List<GroupResponseDto> groups = studentFacade.getGroups(studentId);
        model.addAttribute("student", studentFacade.findById(studentId));
        model.addAttribute("groups", groups);
        return "pages/student/student_details";
    }

    @GetMapping("/group/students/{id}")
    public String allStudentFromGroup(Model model, @PathVariable Long id) {
        List<StudentResponseDto> students = groupFacade.getStudents(id);
        model.addAttribute("group", groupFacade.findById(id));
        model.addAttribute("students", students);
        return "pages/student/students_group";
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnTitles, PageData<GroupResponseDto> response) {
        List<HeaderData> headerDataList = new ArrayList<>();

        for (HeaderName headerName : columnTitles) {
            HeaderData data = new HeaderData();
            data.setHeaderName(headerName.getColumnName());
            if (StringUtils.isBlank(headerName.getTableName())) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(headerName.getDbName());
                if (response.getSort().equals(headerName.getTableName())) {
                    data.setActive(true);
                    data.setOrder(response.getOrder());
                } else {
                    data.setActive(false);
                    data.setOrder(DEFAULT_ORDER_PARAM_VALUE);
                }
            }
            headerDataList.add(data);
        }
        return headerDataList;
    }
}