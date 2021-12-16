package ua.com.alevel.service.impl;

import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void create(Student entity) {
        studentDao.create(entity);
    }

    @Override
    public void update(Student entity) {
        if (!studentDao.existById(entity.getId())) {
            throw new EntityNotFoundException("student not found");
        }
        studentDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!studentDao.existById(id)) {
            throw new EntityNotFoundException("student not found");
        }
        studentDao.delete(id);
    }

    @Override
    public Student findById(Long id) {
        if (!studentDao.existById(id)) {
            throw new EntityNotFoundException("student not found");
        }
        return studentDao.findById(id);
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        DataTableResponse<Student> dataTableResponse = studentDao.findAll(request);
        long count = studentDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }

    @Override
    public List<Group> getGroups(Long id) {
        return studentDao.getGroups(id);
    }
}