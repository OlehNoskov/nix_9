package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.repository.GroupRepository;
import ua.com.alevel.persistence.repository.StudentRepository;
import ua.com.alevel.service.GroupService;

import java.util.*;

@Service
public class GroupServiceImpl implements GroupService {

    private final CrudRepositoryHelper<Group, GroupRepository> groupRepositoryHelper;
    private final CrudRepositoryHelper<Student, StudentRepository> studentRepositoryHelper;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public GroupServiceImpl(CrudRepositoryHelper<Group, GroupRepository> groupRepositoryHelper,
                            CrudRepositoryHelper<Student, StudentRepository> studentRepositoryHelper,
                            GroupRepository groupRepository, StudentRepository studentRepository) {
        this.groupRepositoryHelper = groupRepositoryHelper;
        this.studentRepositoryHelper = studentRepositoryHelper;
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(Group entity) {
        groupRepositoryHelper.create(groupRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Group entity) {
        groupRepositoryHelper.update(groupRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        groupRepositoryHelper.delete(groupRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Group> findById(Long id) {
        return groupRepositoryHelper.findById(groupRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Group> findAll(DataTableRequest dataTableRequest) {
        return groupRepositoryHelper.findAll(groupRepository, dataTableRequest);
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addStudent(Long groupId, Long studentId) {
        Group group = groupRepositoryHelper.findById(groupRepository, groupId).get();
        Student student = studentRepositoryHelper.findById(studentRepository, studentId).get();
        group.addStudent(student);
        groupRepositoryHelper.update(groupRepository, group);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void removeStudent(Long groupId, Long studentId) {
        Group group = groupRepositoryHelper.findById(groupRepository, groupId).get();
        Student student = studentRepositoryHelper.findById(studentRepository, studentId).get();
        group.removeStudent(student);
        groupRepositoryHelper.update(groupRepository, group);
    }

    @Override
    public List<Student> getStudents(Long id) {
        return groupRepositoryHelper.findById(groupRepository,id).get().getStudents();
    }
}