package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.persistence.type.Country;
import com.project.medicalanalize.persistence.type.Sex;

import lombok.Getter;

import java.util.Date;

public class UserResponseDto extends ResponseDto {

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    private Date birthDay;

    @Getter
    private Sex sex;

    @Getter
    private String phone;

    @Getter
    private Country country;

    @Getter
    private String day;

    @Getter
    private String months;

    @Getter
    private String year;

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
}