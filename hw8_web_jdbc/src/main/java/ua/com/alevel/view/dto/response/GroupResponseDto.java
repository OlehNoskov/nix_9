package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Group;

public class GroupResponseDto extends ResponseDto{

    private String name;
    private Integer studentCount;

    public GroupResponseDto() { }

    public GroupResponseDto(Group group) {
        setId(group.getId());
        setCreated(group.getCreated());
        setUpdated(group.getUpdated());
        setVisible(group.getVisible());
        this.name = group.getNameGroup();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    @Override
    public String toString() {
        return "GroupResponseDto{" +
                "name='" + name + '\'' +
                ", studentCount=" + studentCount +
                '}';
    }
}