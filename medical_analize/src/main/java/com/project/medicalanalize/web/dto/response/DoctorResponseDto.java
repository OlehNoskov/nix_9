package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.sex.Sex;

import java.math.BigDecimal;
import java.util.Date;

public class DoctorResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private Date birthDay;
    private Sex sex;
    private BigDecimal phone;
    private String country;

    DoctorResponseDto(){}

    public DoctorResponseDto(Doctor doctor){
        setId(doctor.getId());
        setCreated(doctor.getCreated());
        setUpdated(doctor.getUpdated());
        setVisible(doctor.getVisible());
        this.firstName = doctor.getFirstName();
        this.lastName = doctor.getLastName();
        this.birthDay = doctor.getBirthDay();
        this.sex = doctor.getSex();
        this.phone = doctor.getPhone();
        this.country = doctor.getCountry();
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

    public BigDecimal getPhone() {
        return phone;
    }

    public void setPhone(BigDecimal phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}