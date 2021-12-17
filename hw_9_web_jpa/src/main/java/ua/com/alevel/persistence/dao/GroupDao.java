package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Group;

import java.util.Map;

public interface GroupDao extends BaseDao<Group> {
    Map<Long, String> findStudentByGroupId(Long id);
}