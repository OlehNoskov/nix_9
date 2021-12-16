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
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.*;

import java.util.ArrayList;
import java.util.List;
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
        List<Group> groups = new ArrayList<>();
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
        List<Group> groups = new ArrayList<>();
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
    public StudentResponseDto findById(long id) {
        return new StudentResponseDto(studentService.findById(id));
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
    public List<GroupResponseDto> getGroups(Long id) {
        List<Group> groups = studentService.getGroups(id);
        List<GroupResponseDto> list = new ArrayList<>();
        for (Group group : groups) {
            GroupResponseDto groupResponseDto = new GroupResponseDto(group);
            list.add(groupResponseDto);
        }
        return list;
    }
}