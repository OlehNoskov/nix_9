package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Student;

import java.util.Collections;
import java.util.List;

@Service
public class StudentDaoImpl implements StudentDao {
    @Override
    public void create(Student entity) {

    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Student findById(Long id) {
        return null;
    }

    @Override
    public List<Student> findAll() {

        return Collections.emptyList();
    }

    @Override
    public void deleteAllByGroupId(Long groupId) {

    }

    @Override
    public List<Student> findAllByGroupId(Long groupId) {
        return Collections.emptyList();
    }
}