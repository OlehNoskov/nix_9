package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.persistence.type.Country;
import com.project.medicalanalize.persistence.type.Sex;

import java.util.Date;

public class UserResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private Date birthDay;
    private Sex sex;
    private String phone;
    private Country country;

    public UserResponseDto(User user) {

        setId(user.getId());
        setCreated(user.getCreated());
        setUpdated(user.getUpdated());
        setVisible(user.getVisible());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birthDay = user.getBirthDay();
        this.sex = user.getSex();
        this.phone = user.getPhone();
        this.country = user.getCountry();
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