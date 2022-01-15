package com.project.medicalanalize.persistence.entity.user;

import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.repository.type.RoleType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("DOCTOR")
public class Doctor extends User {

    @Getter
    @Setter
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders;

    public Doctor() {
        super();
        setRoleType(RoleType.ROLE_DOCTOR);
        this.orders = new HashSet<>();
    }
}