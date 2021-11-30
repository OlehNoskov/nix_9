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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupFacadeImpl implements GroupFacade {

    private final GroupService groupService;

    public GroupFacadeImpl(GroupService service) {
        this.groupService = service;
    }

    @Override
    public void create(GroupRequestDto groupRequestDto) {
        Group group = new Group();
        group.setNameGroup(groupRequestDto.getNameGroup());
        groupService.create(group);
    }

    @Override
    public void update(GroupRequestDto groupRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public GroupResponseDto findById(Long id) {
        return null;
    }

    @Override
    public PageData<GroupResponseDto> findAll(WebRequest request) {

        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setPageSize(pageAndSizeData.getSize());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Group> all = groupService.findAll(dataTableRequest);
        List<GroupResponseDto> list = all.getItems().
                stream().
                map(GroupResponseDto::new).
                peek(dto -> dto.setStudentCount((Integer) all.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());

        PageData<GroupResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());

        System.out.println("pageData = " + pageData);
        return pageData;
    }
}