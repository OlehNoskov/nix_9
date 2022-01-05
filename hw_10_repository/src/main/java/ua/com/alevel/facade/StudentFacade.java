package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.util.Map;
import java.util.Set;

public interface StudentFacade extends BaseFacade<StudentRequestDto, StudentResponseDto>{

    Set<GroupResponseDto> getGroups(Long studentId);
    Set<StudentResponseDto> findAll();
}