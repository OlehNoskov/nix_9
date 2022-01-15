package com.project.medicalanalize.persistence.entity.user;

import com.project.medicalanalize.persistence.entity.feedback.Feedback;
import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.repository.type.RoleType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User {

    @Getter
    @Setter
    private Integer height;

    @Getter
    @Setter
    private Integer weight;

    @Getter
    @Setter
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Feedback> feedbacks;

    @Getter
    @Setter
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders;

    public Patient() {
        super();
        setRoleType(RoleType.ROLE_PATIENT);
        this.orders = new HashSet<>();
        this.feedbacks = new HashSet<>();
    }
}