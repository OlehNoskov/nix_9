package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;

public interface GroupDao extends BaseDao<Group>{
    List<Student> getStudents(Long id);

    void addStudent(Long groupId, Long studentId);

    void removeStudent(Long groupId, Long studentId);
}