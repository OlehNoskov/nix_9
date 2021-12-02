package ua.com.alevel.entity;

import ua.com.alevel.type.GroupType;



//@Entity
//@Table(name = "groups")
public class Group extends BaseEntity{
//
//    @Enumerated(EnumType.STRING)//Хранение значений enum в качестве строки(по умолчания как число(ORDINAL))
//    @Column(name = "group_type", nullable = false, updatable = false)
    private GroupType groupType;

//    @Column(nullable = false, unique = true)//unique-в базе мы не может хранить одинаковые имена групп.
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