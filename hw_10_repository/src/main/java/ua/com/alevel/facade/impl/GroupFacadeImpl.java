package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;


import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupFacadeImpl implements GroupFacade {

    private final GroupService groupService;

    public GroupFacadeImpl(GroupService groupService) {
        this.groupService = groupService;
    }
//    private final StudentService studentService;

//    public GroupFacadeImpl(GroupService groupService, StudentService studentService) {
//        this.groupService = groupService;
//        this.studentService = studentService;
//    }

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
        group.setUpdated(new Timestamp(System.currentTimeMillis()));
        groupService.update(group);
    }

    @Override
    public void delete(long id) {
        groupService.delete(id);
    }

    @Override
    public GroupResponseDto findById(long id) {
        System.out.println("Find group facade");
        return new GroupResponseDto(groupService.findById(id));
    }

//    @Override
//    public Set<StudentResponseDto> getStudents(Long groupId) {
//        return null;
//    }

//    @Override
//    public Set<StudentResponseDto> getStudents(Long groupId) {
//        return null;
//    }

    @Override
    public Map<Long, String> findStudentsByGroupId(Long groupId) {
        return groupService.findStudentsByGroupId(groupId);
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
        List<GroupResponseDto> groups = all.getItems().
                stream().
                map(GroupResponseDto::new).
                peek(dto -> dto.setStudentCount((Integer) all.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());

        PageData<GroupResponseDto> pageData = new PageData<>();
        pageData.setItems(groups);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());

        return pageData;
    }



//

//    @Override
//    public Set<StudentResponseDto> getStudents(Long groupId) {
//        Set<Student> students = groupService.findById(groupId).get().getStudents();
//        Set<StudentResponseDto> list = new HashSet<>();
//        for (Student student : students) {
//            StudentResponseDto studentResponseDto = new StudentResponseDto(student);
//            list.add(studentResponseDto);
//        }
//        return list;
//    }
}