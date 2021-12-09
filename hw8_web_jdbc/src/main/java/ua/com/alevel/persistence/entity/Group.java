package ua.com.alevel.persistence.entity;

public class Group extends BaseEntity {

    private String name;

    public Group() {
        super();
    }

    public String getNameGroup() {
        return name;
    }

    public void setNameGroup(String name) {
        this.name = name;
    }
}