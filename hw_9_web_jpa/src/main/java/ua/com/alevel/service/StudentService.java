package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;

public interface StudentService extends BaseService<Student>{
    List<Group> getGroups(Long id);
}