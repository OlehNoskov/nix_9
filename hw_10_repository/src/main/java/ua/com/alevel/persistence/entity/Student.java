package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    private Integer age;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //не обязательно писать fetch = FetchType.LAZY, так как он default.
    private Set<Group> groups;

    public Student() {
        super();
        this.groups = new HashSet<>();
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(firstname, student.firstname) && Objects.equals(lastname, student.lastname) && Objects.equals(age, student.age) && Objects.equals(groups, student.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstname, lastname, age, groups);
    }
}