package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.util.Set;

public interface GroupFacade extends BaseFacade<GroupRequestDto, GroupResponseDto>{

    Set<StudentResponseDto> getStudents(Long id);

    void addStudent(Long groupId, Long studentId);

    void removeStudent(Long groupId, Long studentId);
}