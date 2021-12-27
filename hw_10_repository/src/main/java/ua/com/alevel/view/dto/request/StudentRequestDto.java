package ua.com.alevel.view.dto.request;

import java.util.List;

public class StudentRequestDto extends RequestDto{

    private Long studentId;
    private String firstname;
    private String lastname;
    private int age;
    private List<Long> groupsIds;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Long> getGroupsIds() {
        return groupsIds;
    }

    public void setGroupsIds(List<Long> groupsIds) {
        this.groupsIds = groupsIds;
    }

    public Long getStudentId() {
        return studentId;
    }
}