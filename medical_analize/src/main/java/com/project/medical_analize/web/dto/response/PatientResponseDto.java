package com.project.medical_analize.web.dto.response;

import com.project.medical_analize.persistence.entity.user.Patient;
import com.project.medical_analize.persistence.sex.Sex;

import java.math.BigDecimal;
import java.util.Date;

public class PatientResponseDto extends ResponseDto{

    private String firstName;
    private String lastName;
    private Date birthDay;
    private Sex sex;
    private BigDecimal phone;
    private Integer height;
    private Integer weight;
    private String country;

    public PatientResponseDto(){}

    public PatientResponseDto(Patient patient){
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

    public BigDecimal getPhone() {
        return phone;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getCountry() {
        return country;
    }
}