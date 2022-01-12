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
@DiscriminatorValue("PATIENT")
public class Patient extends User {

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

    private Integer height;

    private Integer weight;

    @Enumerated(EnumType.STRING)
    private Country country;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders;

    public Patient() {
        super();
        setRoleType(RoleType.ROLE_PATIENT);
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}






//@Entity
//@DiscriminatorValue("PATIENT")
//public class Patient extends User {
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

//    @Transient
//    private Integer day;
//
//    @Transient
//    private Integer month;
//
//    @Transient
//    private Integer year;


//    @Enumerated(EnumType.STRING)
//    private Sex sex;
//
//    @Column(name = "phone")
//    private String phone;
//
//    private Integer height;
//
//    private Integer weight;
//
//    @Enumerated(EnumType.STRING)
//    private Country country;

//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "patient_id", nullable = false)
//    private User user;

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Order> orders;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patients")
    //mappedBy = "patients" --> Set <Patient> patients in Doctor class
//    private Set<Doctor> doctors;
//
//    public Patient() {
//        super();
//        setRoleType(RoleType.ROLE_PATIENT);
//        this.orders = new HashSet<>();
//        this.doctors = new HashSet<>();
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
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public Set<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(Set<Order> orders) {
//        this.orders = orders;
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
//    public Set<Doctor> getDoctors() {
//        return doctors;
//    }
//
//    public void setDoctors(Set<Doctor> doctors) {
//        this.doctors = doctors;
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
//    public Integer getHeight() {
//        return height;
//    }
//
//    public void setHeight(Integer height) {
//        this.height = height;
//    }
//
//    public Integer getWeight() {
//        return weight;
//    }
//
//    public void setWeight(Integer weight) {
//        this.weight = weight;
//    }
//
//    public Country getCountry() {
//        return country;
//    }
//
//    public void setCountry(Country country) {
//        this.country = country;
//    }


//    public Integer getDay() {
//        return birthDay.getDay();
//    }
//
//    public void setDay(Integer day) {
//        this.day = day;
//    }
//
//    public Integer getMonth() {
//        return birthDay.getMonth();
//    }
//
//    public void setMonth(Integer month) {
//        this.month = month;
//    }
//
//    public Integer getYear() {
//        return birthDay.getYear();
//    }
//
//    public void setYear(Integer year) {
//        this.year = year;
//    }
