package ua.com.alevel.persistence.entity;

import ua.com.alevel.persistence.type.GroupType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "course2")
public class Group extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "group_type")
    private GroupType groupType;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "course2_students2",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;

    public Group() {
        super();
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}