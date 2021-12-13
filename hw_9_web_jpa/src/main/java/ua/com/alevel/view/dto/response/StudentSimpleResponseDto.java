package ua.com.alevel.view.dto.response;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.persistence.entity.Student;

public class StudentSimpleResponseDto extends ResponseDto {
    private String firstName;
    private String lastName;
    private int age;
    private long groupsCount;

    public StudentSimpleResponseDto(Student student) {
        setId(student.getId());
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.age = student.getAge();
        if (CollectionUtils.isNotEmpty(student.getGroups())) {
            groupsCount = student.getGroups().size();
        }
    }

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

    public long getGroupsCount() {
        return groupsCount;
    }

    public void setGroupsCount(long groupsCount) {
        this.groupsCount = groupsCount;
    }
}