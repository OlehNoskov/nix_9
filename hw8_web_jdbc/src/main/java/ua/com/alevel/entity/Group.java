package ua.com.alevel.entity;

import ua.com.alevel.type.GroupType;

public class Group extends BaseEntity{
    private GroupType groupType;
    private String nameGroup;

    public Group() {
        super();
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }
}