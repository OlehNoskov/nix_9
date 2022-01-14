package com.project.medicalanalize.web.dto.request;

import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.type.Country;
import com.project.medicalanalize.persistence.type.Sex;

import java.util.Date;
import java.util.Set;

public class UserRequestDto extends RequestDto {
    private String firstName;
    private String lastName;
    private Date birthDay;
    private Sex sex;
    private String phone;
    private Country country;
    private Set<Order> orders;

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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}