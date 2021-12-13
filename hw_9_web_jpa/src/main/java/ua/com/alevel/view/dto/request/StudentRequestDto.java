package ua.com.alevel.view.dto.request;

import java.util.List;

public class StudentRequestDto extends RequestDto{

    private String firstName;
    private String lastName;
    private int age;
    private List<Long> groupsIds;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}