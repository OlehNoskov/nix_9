package ua.com.alevel.persistence.entity;

import ua.com.alevel.type.GroupType;

public class Group extends BaseEntity {

    private GroupType groupType;
    private String name;

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
        return name;
    }

    public void setNameGroup(String name) {
        this.name = name;
    }
}