package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Student;

public class StudentResponseDto extends DtoResponse{

    private String name;
    private String lastname;
    private Integer age;

    public StudentResponseDto() {
    }
    public StudentResponseDto(Student student) {
        setId(student.getId());
        setCreated(student.getCreated());
        setUpdated(student.getUpdated());
        setVisible(student.getVisible());
        this.name = student.getFirstname();
        this.lastname = student.getLastname();
        this.age = student.getAge();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}