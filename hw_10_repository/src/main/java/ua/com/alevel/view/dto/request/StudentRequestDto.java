package ua.com.alevel.view.dto.request;

import java.util.List;
import java.util.Set;

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

    public Set<Long> getGroupsIds() {
        return (Set<Long>) groupsIds;
    }

    public void setGroupsIds(List<Long> groupsIds) {
        this.groupsIds = groupsIds;
    }

    public Long getStudentId() {
        return studentId;
    }
}