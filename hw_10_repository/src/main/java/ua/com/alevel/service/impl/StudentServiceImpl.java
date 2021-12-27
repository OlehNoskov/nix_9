package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.repository.StudentRepository;
import ua.com.alevel.service.StudentService;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final CrudRepositoryHelper<Student, StudentRepository> repositoryHelper;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(CrudRepositoryHelper<Student, StudentRepository> repositoryHelper,
                                                            StudentRepository studentRepository) {
        this.repositoryHelper = repositoryHelper;
        this.studentRepository = studentRepository;
    }

    @Override
    public void create(Student entity) {
        repositoryHelper.create(studentRepository, entity);
    }

    @Override
    public void update(Student entity) {
        repositoryHelper.update(studentRepository, entity);
    }

    @Override
    public void delete(Long id) {
        repositoryHelper.delete(studentRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(Long id) {
        return repositoryHelper.findById(studentRepository, id);
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest dataTableRequest) {
        return repositoryHelper.findAll(studentRepository, dataTableRequest);
    }

    @Override
    public void addRelation(Long groupId, Long studentId) {
        repositoryHelper.addRelation(groupId, studentId);
        Student student = studentRepository.findById(studentId).get();

        if (student.getGroups().size() != 0) {
            student.setVisible(true);
            update(student);
        }
    }

    @Override
    public void removeRelation(Long groupId, Long studentId) {
        repositoryHelper.removeRelation(groupId, studentId);
        Student student = studentRepository.findById(studentId).get();

        if (student.getGroups().size() == 0) {
            student.setVisible(false);
            update(student);
        }
    }
}