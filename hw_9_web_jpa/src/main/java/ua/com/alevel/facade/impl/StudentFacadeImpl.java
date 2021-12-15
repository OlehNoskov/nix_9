package ua.com.alevel.facade.impl;

import org.apache.commons.collections4.CollectionUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentFullResponseDto;
import ua.com.alevel.view.dto.response.StudentSimpleResponseDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;
    private final GroupService groupService;

    public StudentFacadeImpl(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @Override
    public void create(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setAge(studentRequestDto.getAge());
        Set<Group> groups = new HashSet<>();
        if (CollectionUtils.isNotEmpty(studentRequestDto.getGroupsIds())) {
            for (Long groupsId : studentRequestDto.getGroupsIds()) {
                groups.add(groupService.findById(groupsId));
            }
        }
        student.setGroups(groups);
        studentService.create(student);
    }

    @Override
    public void update(StudentRequestDto studentRequestDto, long id) {
        Student student = studentService.findById(id);
        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setAge(studentRequestDto.getAge());
        Set<Group> groups = new HashSet<>();
        if (CollectionUtils.isNotEmpty(studentRequestDto.getGroupsIds())) {
            for (Long groupsId : studentRequestDto.getGroupsIds()) {
                groups.add(groupService.findById(groupsId));
            }
        }
        student.setGroups(groups);
        studentService.update(student);
    }

    @Override
    public void delete(long id) {
        studentService.delete(id);
    }

    @Override
    public StudentFullResponseDto findById(long id) {
        return new StudentFullResponseDto(studentService.findById(id));
    }

    @Override
    public PageData<StudentSimpleResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.generateDataTableRequest(request);
        DataTableResponse<Student> tableResponse = studentService.findAll(dataTableRequest);
        List<StudentSimpleResponseDto> students = tableResponse.getItems()
                .stream()
                .map(StudentSimpleResponseDto::new)
                .collect(Collectors.toList());
        PageData<StudentSimpleResponseDto> pageData = (PageData<StudentSimpleResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(students);
        return pageData;
    }
}