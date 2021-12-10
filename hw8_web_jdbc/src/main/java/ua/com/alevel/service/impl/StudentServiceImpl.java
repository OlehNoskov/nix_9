package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;

import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void create(Student student) {
        studentDao.create(student);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void delete(Long id) {
        studentDao.delete(id);
    }

    @Override
    public Student findById(Long id) {
        return studentDao.findById(id);
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        return studentDao.findAll(request);
    }
}