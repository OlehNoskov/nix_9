package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import java.util.Map;
import java.util.Set;

public interface GroupService extends BaseService<Group>{
    void addStudent(Long groupId, Long studentId);

    void removeStudent(Long groupId, Long studentId);

    Set<Student> getStudents(Long groupId);

    Map<Long, String> findStudentsByGroupId(Long id);

}