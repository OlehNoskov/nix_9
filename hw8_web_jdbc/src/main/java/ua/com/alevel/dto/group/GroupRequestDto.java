package ua.com.alevel.dto.group;

import ua.com.alevel.dto.RequestDto;
import ua.com.alevel.type.GroupType;

public class GroupRequestDto extends RequestDto {
    private GroupType groupType;
    private String name;

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
}