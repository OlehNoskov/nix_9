package com.project.medicalanalize.persistence.entity.user;

import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.type.Country;
import com.project.medicalanalize.persistence.type.Sex;
import com.project.medicalanalize.persistence.repository.type.RoleType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("DOCTOR")
public class Doctor extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_day")
    private Date birthDay;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Country country;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders;

    public Doctor() {
        super();
        setRoleType(RoleType.ROLE_DOCTOR);
        this.orders = new HashSet<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}


//@Entity
//@DiscriminatorValue("DOCTOR")
//public class Doctor extends User {
//
//    @Column(name = "first_name")
//    private String firstName;
//
//    @Column(name = "last_name")
//    private String lastName;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "birth_day")
//    private Date birthDay;
//
//    @Enumerated(EnumType.STRING)
//    private Sex sex;
//
//    @Column(name = "phone")
//    private String phone;
//
//    @Enumerated(EnumType.STRING)
//    private Country country;
//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "doctor_patient",
//            joinColumns = @JoinColumn(name = "doctor_id"),
//            inverseJoinColumns = @JoinColumn(name = "patient_id"))
//    private Set<Patient> patients;
//
//    public Doctor() {
//        super();
//        setRoleType(RoleType.ROLE_DOCTOR);
//        patients = new HashSet<>();
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public Sex getSex() {
//        return sex;
//    }
//
//    public void setSex(Sex sex) {
//        this.sex = sex;
//    }
//
//    public Set<Patient> getPatients() {
//        return patients;
//    }
//
//    public void setPatients(Set<Patient> patients) {
//        this.patients = patients;
//    }
//
//    public Date getBirthDay() {
//        return birthDay;
//    }
//
//    public void setBirthDay(Date birthDay) {
//        this.birthDay = birthDay;
//    }
//
//    public Country getCountry() {
//        return country;
//    }
//
//    public void setCountry(Country country) {
//        this.country = country;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//}