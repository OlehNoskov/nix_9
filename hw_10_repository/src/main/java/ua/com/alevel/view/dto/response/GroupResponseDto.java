package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Group;

public class GroupResponseDto extends ResponseDto{
    private String name;
    private Long studentCount;

    public GroupResponseDto() { }

    public GroupResponseDto(Group group) {
        setId(group.getId());
        setCreated(group.getCreated());
        setUpdated(group.getUpdated());
        setVisible(group.getVisible());
        this.name = group.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Long studentCount) {
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