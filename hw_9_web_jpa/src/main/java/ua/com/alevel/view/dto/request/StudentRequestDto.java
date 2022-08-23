package ua.com.alevel.view.dto.request;

import java.util.List;

public class StudentRequestDto extends RequestDto{

    private String firstname;
    private String lastname;
    private int age;
    private List<Long> groupsIds;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public List<Long> getGroupsIds() {
        return groupsIds;
    }

}