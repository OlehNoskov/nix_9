package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.PageData;

@Service
public class GroupFacadeImpl implements GroupFacade {

    private final GroupService service;

    public GroupFacadeImpl(GroupService service) {
        this.service = service;
    }

    @Override
    public void create(GroupRequestDto groupRequestDto) {

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
        return null;
    }
}