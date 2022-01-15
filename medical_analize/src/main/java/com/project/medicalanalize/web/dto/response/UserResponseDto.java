package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.persistence.type.Country;
import com.project.medicalanalize.persistence.type.Sex;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserResponseDto extends ResponseDto {

    private String day;
    private String months;
    private String year;

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

    public Date getBirthDay() throws ParseException {
//        String s = getYear()+"-"+getDay()+"-"+getMonths();
//        SimpleDateFormat format = new SimpleDateFormat();
//        format.applyPattern("yyyy-MM-dd");
//        return format.parse(s);
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

    public String getDay() {
        return day;
    }

    public String getMonths() {
        return months;
    }

    public String getYear() {
        return year;
    }
}