package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import java.util.List;

public interface StudentDao extends BaseDao<Student>{
    List<Group> getGroups(Long id);
}