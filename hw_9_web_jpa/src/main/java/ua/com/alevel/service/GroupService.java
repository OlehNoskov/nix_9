package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Group;

import java.util.Map;

public interface GroupService extends BaseService<Group>{
    Map<Long, String> findStudentByGroupId(Long id);
}