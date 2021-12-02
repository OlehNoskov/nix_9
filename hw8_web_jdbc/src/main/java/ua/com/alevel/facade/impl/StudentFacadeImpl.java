package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentFacadeImpl implements StudentFacade {

    private final GroupService groupService;
    private final StudentService studentService;

    public StudentFacadeImpl(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @Override
    public void create(StudentRequestDto studentRequestDto) {
        Group group = groupService.findById(studentRequestDto.getGroupId());
        Student student = new Student();
        student.setFirstname(studentRequestDto.getFirstname());
        student.setLastname(studentRequestDto.getLastname());
        student.setAge(studentRequestDto.getAge());
        student.setGroup(group);
        studentService.create(student);
    }

    @Override
    public void update(StudentRequestDto studentRequestDto, Long id) {
    Student student = studentService.findById(id);
    student.setFirstname(studentRequestDto.getFirstname());
    student.setLastname(studentRequestDto.getLastname());
    student.setAge(studentRequestDto.getAge());
    studentService.update(student);
    }

    @Override
    public void delete(Long id) {
    studentService.delete(id);
    }

    @Override
    public StudentResponseDto findById(Long id) {
        return new StudentResponseDto(studentService.findById(id));
    }

    @Override
    public List<StudentResponseDto> findAll() {
        return convertToDtoByEntity(studentService.findAll());
    }

    @Override
    public List<StudentResponseDto> findAllByGroupId(Long groupId) {
        return convertToDtoByEntity(studentService.findAllByGroupId(groupId));
    }

    private List<StudentResponseDto> convertToDtoByEntity(List<Student> students) {
        return students.stream()
                .map(StudentResponseDto::new)
                .collect(Collectors.toList());
    }
}