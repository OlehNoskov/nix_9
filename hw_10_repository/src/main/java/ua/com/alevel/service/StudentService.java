package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Student;

public interface StudentService extends BaseService<Student>{

    void addRelation(Long groupId, Long studentId);

    void removeRelation(Long groupId, Long studentId);
}