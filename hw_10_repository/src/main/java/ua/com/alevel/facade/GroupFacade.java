package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.util.List;
import java.util.Set;

public interface GroupFacade extends BaseFacade<GroupRequestDto, GroupResponseDto>{

    void addStudent(Long groupId, Long studentId);

    void removeStudent(Long groupId, Long studentId);

    List<StudentResponseDto> getStudents(Long groupId);

    List<GroupResponseDto> findAll();
}