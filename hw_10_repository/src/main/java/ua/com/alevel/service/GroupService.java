package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Group;

import java.util.List;
import java.util.Set;

public interface GroupService extends BaseService<Group>{
    List<Group> findByStudents(Set<Long> students);
    Set<Group> findByStudentsIds(Set<Long> students);
    Set<Group> findByVisibleTrue();
}