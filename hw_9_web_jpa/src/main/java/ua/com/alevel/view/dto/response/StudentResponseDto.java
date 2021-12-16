package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Student;

public class StudentResponseDto extends ResponseDto{

    private String firstname;
    private String lastname;
    private Integer age;

    public StudentResponseDto() { }

    public StudentResponseDto(Student student) {
        setId(student.getId());
        setCreated(student.getCreated());
        setUpdated(student.getUpdated());
        setVisible(student.getVisible());
        this.firstname = student.getFirstName();
        this.lastname = student.getLastName();
        this.age = student.getAge();
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
}