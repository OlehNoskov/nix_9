package ua.com.alevel.facade.impl;

import org.apache.commons.collections4.CollectionUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupFacadeImpl implements GroupFacade {

    private final GroupService groupService;
    private final StudentService studentservice;

    public GroupFacadeImpl(GroupService groupService, StudentService studentservice) {
        this.groupService = groupService;
        this.studentservice = studentservice;
    }

    @Override
    public void create(GroupRequestDto groupRequestDto) {
        Group group = new Group();
        group.setName(groupRequestDto.getName());
        groupService.create(group);
    }

    @Override
    public void update(GroupRequestDto groupRequestDto, long id) {
        Group group = groupService.findById(id);
        group.setName(groupRequestDto.getName());
        if (CollectionUtils.isNotEmpty(groupRequestDto.getStudentsIds())) {
            Set<Student> students = new HashSet<>();
            for (Long studentsId : groupRequestDto.getStudentsIds()) {
                students.add(studentservice.findById(studentsId));
            }
            group.setStudents(students);
        }
    }

    @Override
    public void delete(long id) {
        groupService.delete(id);
    }

    @Override
    public GroupResponseDto findById(long id) {
        return new GroupResponseDto(groupService.findById(id));
    }

    @Override
    public PageData<GroupResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Group> all = groupService.findAll(dataTableRequest);

        List<GroupResponseDto> list = all.getItems().
                stream().
                map(GroupResponseDto::new).
                collect(Collectors.toList());

        PageData<GroupResponseDto> pageData = new PageData<>();
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
    public Set<StudentResponseDto> getStudents(Long id) {
        Set<Student> students = groupService.getStudents(id);
        Set<StudentResponseDto> list = new HashSet<>();
        for (Student student : students) {
            StudentResponseDto studentResponseDto = new StudentResponseDto(student);
            list.add(studentResponseDto);
        }
        return list;
    }

    @Override
    public void addStudent(Long groupId, Long studentId) {
        groupService.addStudent(groupId,studentId);
    }

    @Override
    public void removeStudent(Long groupId, Long studentId) {
     groupService.removeStudent(groupId,studentId);
    }
}