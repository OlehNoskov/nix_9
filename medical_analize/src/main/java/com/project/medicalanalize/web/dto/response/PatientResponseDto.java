package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.type.Country;
import com.project.medicalanalize.persistence.type.Sex;

import java.util.Date;
import java.util.Set;

public class PatientResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private Date birthDay;
    private Sex sex;
    private String phone;
    private Integer height;
    private Integer weight;
    private Country country;
    private Set<Order> orders;

    public PatientResponseDto() {
    }

    public PatientResponseDto(Patient patient) {
        setId(patient.getId());
        setCreated(patient.getCreated());
        setUpdated(patient.getUpdated());
        setVisible(patient.getVisible());
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.birthDay = patient.getBirthDay();
        this.sex = patient.getSex();
        this.phone = patient.getPhone();
        this.height = patient.getHeight();
        this.weight = patient.getWeight();
        this.country = patient.getCountry();
        this.orders = patient.getOrders();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public Sex getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Country getCountry() {
        return country;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}