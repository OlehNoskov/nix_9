package com.project.medical_analize.persistence.entity.patient;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.medical_analize.persistence.entity.BaseEntity;
import com.project.medical_analize.persistence.entity.doctor.Doctor;
import com.project.medical_analize.persistence.entity.order.Order;
import com.project.medical_analize.persistence.sex.Sex;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "birth_day")
//    private Date birthDay;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "phone")
    private Integer phone;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Order> orders;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patients")
                        //mappedBy = "patients" --> Set <Patient> patients in Doctor class
    private Set<Doctor> doctors;

    public Patient() {
        super();
        orders = new HashSet<>();       //???
        doctors = new HashSet<>();      //???
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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
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

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }
}