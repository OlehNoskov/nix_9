package ua.com.alevel.dto.group;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.type.GroupType;

public class GroupResponseDto extends ResponseDto {
    private GroupType groupType;
    private String name;

    public GroupResponseDto(Group group){
        this.groupType = group.getGroupType();
        this.name = group.getNameGroup();
        super.setId(group.getId());
        super.setCreated(group.getCreated());
        super.setUpdated(group.getUpdated());
    }

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