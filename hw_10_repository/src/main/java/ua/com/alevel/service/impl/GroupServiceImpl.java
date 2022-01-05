package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.repository.GroupRepository;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

//    private final CrudRepositoryHelper<Group, GroupRepository> groupRepositoryHelper;
//    private final CrudRepositoryHelper<Student, StudentRepository> studentRepositoryHelper;
//    private final GroupRepository groupRepository;
//    private final StudentRepository studentRepository;
//
//    public GroupServiceImpl(CrudRepositoryHelper<Group, GroupRepository> groupRepositoryHelper,
//                            CrudRepositoryHelper<Student, StudentRepository> studentRepositoryHelper,
//                            GroupRepository groupRepository, StudentRepository studentRepository) {
//        this.groupRepositoryHelper = groupRepositoryHelper;
//        this.studentRepositoryHelper = studentRepositoryHelper;
//        this.groupRepository = groupRepository;
//        this.studentRepository = studentRepository;
//    }

    private final CrudRepositoryHelper<Group, GroupRepository> groupRepositoryHelper;
    private final GroupRepository groupRepository;
    private final StudentService studentService;

    public GroupServiceImpl(CrudRepositoryHelper<Group, GroupRepository> groupRepositoryHelper,
                            GroupRepository groupRepository, StudentService studentService) {
        this.groupRepositoryHelper = groupRepositoryHelper;
        this.groupRepository = groupRepository;
        this.studentService = studentService;
    }


    @Override
    public void create(Group entity) {
        groupRepositoryHelper.create(groupRepository, entity);
    }

    @Override
    public void update(Group entity) {
        groupRepositoryHelper.update(groupRepository, entity);
    }

    @Override
    public void delete(Long id) {
//        groupRepositoryHelper.delete(groupRepository, id);
        Group group = groupRepositoryHelper.findById(groupRepository, id).get();
        List<Student> listStudents = group.getStudents().stream().filter(student -> studentService.findAllGroupsByStudentId(student.getId()).size() == 1).collect(Collectors.toList());
        group.getStudents().retainAll(listStudents);
        groupRepositoryHelper.update(groupRepository, group);
        groupRepositoryHelper.delete(groupRepository, id);
    }

//    @Override
//    public Optional<Group> findById(Long id) {
//        return groupRepositoryHelper.findById(groupRepository, id);
//    }

    @Override
    public Group findById(Long id) {
        System.out.println("Group service find!");
        return groupRepositoryHelper.findById(groupRepository, id).get();
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest dataTableRequest) {
        return groupRepositoryHelper.findAll(groupRepository, dataTableRequest);
    }

//    @Override
//    public void addStudent(Long groupId, Long studentId) {
//        Group group = groupRepositoryHelper.findById(groupRepository, groupId).get();
//        Student student = studentRepositoryHelper.findById(studentRepository, studentId).get();
//        group.addStudent(student);
//        groupRepositoryHelper.update(groupRepository, group);
//    }
//
//    @Override
//    public void removeStudent(Long groupId, Long studentId) {
//        Group group = groupRepositoryHelper.findById(groupRepository, groupId).get();
//        Student student = studentRepositoryHelper.findById(studentRepository, studentId).get();
//        group.removeStudent(student);
//        groupRepositoryHelper.update(groupRepository, group);
//    }
//
//    @Override
//    public Set<Student> getStudents(Long groupId) {
//        return groupRepositoryHelper.findById(groupRepository, groupId).get().getStudents();
//    }

    @Override
    public Map<Long, String> findStudentsByGroupId(Long groupId) {
        Map<Long, String> map = new HashMap<>();
        Set<Student> students = findById(groupId).getStudents();
        for (Student student : students) {
            map.put(student.getId(), student.getFirstName() + " "
                    + student.getLastname()
                    + " " + student.getAge());
        }
        return map;
    }
}