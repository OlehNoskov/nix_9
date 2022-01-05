package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;
import java.util.Set;

public interface GroupService extends BaseService<Group>{

    void addStudent(Long groupId, Long studentId);

    void removeStudent(Long groupId, Long studentId);

    List<Group> findAll();

    Set<Student> getStudents(Long id);
}