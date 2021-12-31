package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import java.util.Map;
import java.util.Set;

public interface GroupService extends BaseService<Group>{

//    Set<Student> getStudents(Long groupId);

    Map<Long, String> findStudentsByGroupId(Long groupId);
}