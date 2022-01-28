package com.project.medicalanalize.persistence.entity.user;

import com.project.medicalanalize.persistence.entity.BaseEntity;
import com.project.medicalanalize.persistence.repository.type.RoleType;
import com.project.medicalanalize.persistence.type.Country;
import com.project.medicalanalize.persistence.type.Sex;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false)
    private RoleType roleType;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "birth_day")
    private Date birthDay;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    private Country country;

    public User() {
        super();
        this.enabled = true;
    }
}