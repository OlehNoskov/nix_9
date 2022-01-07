package com.project.medicalanalize.web.dto.request;

//import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.sex.Sex;

//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.Set;

public class PatientRequestDto extends RequestDto {

    private String firstName;
    private String lastName;
//    private Date birthDay;
    private Sex sex;
//    private BigDecimal phone;
    private Integer height;
    private Integer weight;
    private String country;
//    private Set<Order> orders;

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

//    public Date getBirthDay() {
//        return birthDay;
//    }
//
//    public void setBirthDay(Date birthDay) {
//        this.birthDay = birthDay;
//    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

//    public BigDecimal getPhone() {
//        return phone;
//    }

//    public void setPhone(BigDecimal phone) {
//        this.phone = phone;
//    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

//    public Set<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(Set<Order> orders) {
//        this.orders = orders;
//    }
}