package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.facade.StudentFacade;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;

import ua.com.alevel.service.StudentService;

import ua.com.alevel.util.WebRequestUtil;

import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;

    public StudentFacadeImpl(StudentService service) {
        this.studentService = service;
    }

    @Override
    public void create(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setFirstname(studentRequestDto.getName());
        student.setLastname(studentRequestDto.getLastname());
        student.setAge(studentRequestDto.getAge());
        studentService.create(student);
    }

    @Override
    public void update(StudentRequestDto studentRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public StudentResponseDto findById(Long id) {
        return null;
    }

    @Override
    public PageData<StudentResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setOrder(sortData.getOrder());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setPageSize(dataTableRequest.getPageSize());

        DataTableResponse<Student> all = studentService.findAll(dataTableRequest);

        PageData<StudentResponseDto> pageData = new PageData<>();
        List<StudentResponseDto> items = all.getItems().stream().map(StudentResponseDto::new).collect(Collectors.toList());
        pageData.setItems(items);

        return pageData;
    }
}