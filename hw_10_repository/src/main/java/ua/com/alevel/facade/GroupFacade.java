package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.util.Map;
import java.util.Set;

public interface GroupFacade extends BaseFacade<GroupRequestDto, GroupResponseDto>{
//    void addStudent(Long groupId, Long studentId);
//
//    void removeStudent(Long groupId, Long studentId);

    Set<StudentResponseDto> getStudents(Long groupId);

//    Map<Long, String> findStudentsByGroupId(Long id);
}