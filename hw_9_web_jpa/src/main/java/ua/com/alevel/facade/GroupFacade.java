package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.response.GroupFullResponseDto;
import ua.com.alevel.view.dto.response.GroupSimpleResponseDto;

public interface GroupFacade extends BaseFacade<GroupRequestDto, GroupSimpleResponseDto, GroupFullResponseDto>{
}