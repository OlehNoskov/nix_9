package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.type.GroupType;

import java.util.List;

public class GroupRequestDto {
    private GroupType groupType;
    private String name;
    private List<Long> studentsIds;

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getStudentsIds() {
        return studentsIds;
    }

    public void setStudentsIds(List<Long> studentsIds) {
        this.studentsIds = studentsIds;
    }
}