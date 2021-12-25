package com.project.medical_analize.persistence.entity.user;

import com.project.medical_analize.persistence.repository.type.RoleType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User{

    public Admin() {
        super();
        setRoleType(RoleType.ROLE_ADMIN);
    }
}