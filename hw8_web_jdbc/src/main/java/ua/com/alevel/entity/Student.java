package ua.com.alevel.entity;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;

//@Entity
//@Table(name = "students")
public class Student extends BaseEntity {
//    @Column(name = "first_name")//Создание колонки в базе данных.По умолчанию название поля.
    private String firstname;

//    @Column(name = "last_name")
    private String lastname;

    private Integer age;

//    @ManyToOne//Это не обьект
    private Group group;

    public Student() {
        super();
    }

    public String getFirstname() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}