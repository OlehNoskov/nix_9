package ua.com.alevel.persistence.entity;

public class Group extends BaseEntity {

    private String nameGroup;

    public Group() {
        super();
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }
}