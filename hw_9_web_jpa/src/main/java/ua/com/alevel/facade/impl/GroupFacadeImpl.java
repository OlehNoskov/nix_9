package ua.com.alevel.facade.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.response.GroupFullResponseDto;
import ua.com.alevel.view.dto.response.GroupSimpleResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        group.setGroupType(groupRequestDto.getGroupType());
        group.setName(groupRequestDto.getName());
        groupService.create(group);
    }

    @Override
    public void update(GroupRequestDto groupRequestDto, long id) {
        Group group = groupService.findById(id);
        group.setGroupType(groupRequestDto.getGroupType());
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
    public GroupFullResponseDto findById(long id) {
        return new GroupFullResponseDto(groupService.findById(id));
    }

    @Override
    public PageData<GroupSimpleResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.generateDataTableRequest(request);
        DataTableResponse<Group> tableResponse = groupService.findAll(dataTableRequest);
        List<GroupSimpleResponseDto> departments = tableResponse.getItems()
                .stream()
                .map(GroupSimpleResponseDto::new)
                .collect(Collectors.toList());
        PageData<GroupSimpleResponseDto> pageData = (PageData<GroupSimpleResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(departments);
        return pageData;
    }
}