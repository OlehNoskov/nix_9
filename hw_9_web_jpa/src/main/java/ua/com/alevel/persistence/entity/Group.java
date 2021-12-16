package ua.com.alevel.persistence.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;;

@Entity
@Table(name = "courses")
public class Group extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //не обязательно писать fetch = FetchType.LAZY, так как он default.
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))

    private Set<Student> students;

    public Group() {
        super();
        students = new HashSet<>();
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

    public void setStudents(HashSet<Student> students) {
        this.students = students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.getGroups().add(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.getGroups().remove(this);
    }
}