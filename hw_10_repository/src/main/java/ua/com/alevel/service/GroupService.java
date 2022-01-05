package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Group;

import java.util.Map;

public interface GroupService extends BaseService<Group>{

//    Set<Student> getStudents(Long groupId);

    Map<Long, String> findStudentsByGroupId(Long groupId);
}