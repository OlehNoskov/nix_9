package ua.com.alevel.facade;

import java.util.Map;

public interface GroupFacade extends BaseFacade<GroupRequestDto, GroupResponseDto>{
    Map<Long, String> findStudentByGroupId(Long id);
}