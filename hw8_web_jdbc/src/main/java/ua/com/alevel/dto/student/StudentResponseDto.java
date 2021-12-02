package ua.com.alevel.dto.student;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;

public class StudentResponseDto extends ResponseDto {
    private String firstname;
    private String lastname;
    private Integer age;
    private Group group;

    public StudentResponseDto() {
    }

    public StudentResponseDto(Student student) {
        this.firstname = student.getFirstname();
        this.lastname = student.getLastname();
        this.age = student.getAge();
        if (student.getGroup() != null) {
            this.group = student.getGroup();
        }
        super.setId(student.getId());
        super.setCreated(student.getCreated());
        super.setUpdated(student.getUpdated());
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getAge() {
        return age;
    }

    public Group getGroup() {
        return group;
    }
}