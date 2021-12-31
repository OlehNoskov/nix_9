package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.repository.StudentRepository;
import ua.com.alevel.service.StudentService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Student> findById(Long id) {
//        return repositoryHelper.findById(studentRepository, id);
//    }

    public Student findById(Long id) {
        return repositoryHelper.findById(studentRepository, id).get();
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest dataTableRequest) {
        return repositoryHelper.findAll(studentRepository, dataTableRequest);
    }

//    @Override
//    public Set<Group> getGroups(Long studentId) {
//        return repositoryHelper.findById(studentRepository, studentId).get().getGroups();
//    }

    @Override
    public Map<Long, String> findAllGroupsByStudentId(Long studentId) {
        Map<Long, String> map = new HashMap<>();
        Set<Group> groups = findById(studentId).getGroups();
        for (Group group : groups) {
            map.put(group.getId(), group.getName());
        }
        return map;
    }
}