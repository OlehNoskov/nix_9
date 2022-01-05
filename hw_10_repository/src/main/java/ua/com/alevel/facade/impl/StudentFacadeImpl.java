package ua.com.alevel.facade.impl;

import org.apache.commons.collections4.MapUtils;

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
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.sql.Timestamp;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;

    public StudentFacadeImpl(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
    }

    @Override
    public void create(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setFirstname(studentRequestDto.getFirstname());
        student.setLastname(studentRequestDto.getLastname());
        student.setAge(studentRequestDto.getAge());
        studentService.create(student);
    }

    @Override
    public void update(StudentRequestDto studentRequestDto, long id) {
        Student student = studentService.findById(id).get();
        student.setFirstname(studentRequestDto.getFirstname());
        student.setLastname(studentRequestDto.getLastname());
        student.setAge(studentRequestDto.getAge());
        student.setUpdated(new Timestamp(System.currentTimeMillis()));
        studentService.update(student);
    }

    @Override
    public void delete(long id) {
        studentService.delete(id);
    }

    @Override
    public StudentResponseDto findById(long id) {
        return new StudentResponseDto((Student) studentService.findById(id).get());
    }

    @Override
    public PageData<StudentResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        Map<String, String[]> parameterMap = request.getParameterMap();

        if (MapUtils.isNotEmpty(parameterMap)) {
            String[] params = request.getParameterMap().get("groupId");
        }

        DataTableResponse<Student> all = studentService.findAll(dataTableRequest);

        List<StudentResponseDto> list = all.getItems().
                stream().
                map(StudentResponseDto::new).
                collect(Collectors.toList());

        PageData<StudentResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public List<GroupResponseDto> getGroups(Long studentId) {
        List<Group> groups = studentService.getGroups(studentId);
        List<GroupResponseDto> list  = new ArrayList<>();
        for(Group group: groups){
            GroupResponseDto groupResponseDto =new GroupResponseDto(group);
            list.add(groupResponseDto);
        }
        return list;
    }

    @Override
    public List<StudentResponseDto> findAll() {
        List<Student> allStudents = studentService.findAll();
        List<StudentResponseDto> list = allStudents.stream().map(StudentResponseDto::new).collect(Collectors.toList());
        return list;
    }
}