package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.type.Country;
import com.project.medicalanalize.persistence.type.Sex;

import java.util.Date;

public class DoctorResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private Date birthDay;
    private Sex sex;
    private String phone;
    private Country country;

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

    public Country getCountry() {
        return country;
    }
}