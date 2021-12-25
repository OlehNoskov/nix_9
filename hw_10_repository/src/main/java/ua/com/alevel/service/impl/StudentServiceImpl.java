package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.repository.StudentRepository;
import ua.com.alevel.service.StudentService;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final CrudRepositoryHelper<Student, StudentRepository> repositoryHelper;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(CrudRepositoryHelper<Student, StudentRepository> repositoryHelper, StudentRepository studentRepository) {
        this.repositoryHelper = repositoryHelper;
        this.studentRepository = studentRepository;
    }

    @Override
//    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(Student entity) {
        repositoryHelper.create(studentRepository, entity);
    }

    @Override
//    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Student entity) {
        repositoryHelper.update(studentRepository, entity);
    }

    @Override
//    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        repositoryHelper.delete(studentRepository, id);
    }

    @Override
    public Optional<Group> findById(Long id) {
        return Optional.empty();
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Group> findById(Long id) {
//        return repositoryHelper.findById(studentRepository, id);
//    }

    @Override
//    @Transactional(readOnly = true)
    public DataTableResponse<Student> findAll(DataTableRequest dataTableRequest) {
        return repositoryHelper.findAll(studentRepository, dataTableRequest, Student.class);
    }
}