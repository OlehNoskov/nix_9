package com.project.medicalanalize.web.dto.request;

import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.type.Country;
import com.project.medicalanalize.persistence.type.Sex;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRequestDto extends RequestDto {

    private String firstName;
    private String lastName;
    private Date birthDay;
    private Sex sex;
    private String phone;
    private Country country;
    private Set<Order> orders;
    private String day;
    private String months;
    private String year;
}