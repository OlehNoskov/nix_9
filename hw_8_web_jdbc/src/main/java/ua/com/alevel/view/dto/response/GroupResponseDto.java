package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Group;

public class GroupResponseDto extends DtoResponse{
    private String nameGroup;
    private Integer studentCount;

    public GroupResponseDto() {
    }
    public GroupResponseDto(Group group) {
        setId(group.getId());
//        setCreated(group.getCreated());
//        setUpdated(group.getUpdated());
//        setVisible(group.getVisible());
        this.nameGroup = group.getNameGroup();
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
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
                "nameGroup='" + nameGroup + '\'' +
                ", studentCount=" + studentCount +
                '}';
    }
}