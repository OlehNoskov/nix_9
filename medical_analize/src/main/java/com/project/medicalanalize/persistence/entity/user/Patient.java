package com.project.medicalanalize.persistence.entity.user;

import com.project.medicalanalize.persistence.entity.feedback.Feedback;
import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.repository.type.RoleType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(height, patient.height) && Objects.equals(weight, patient.weight) && Objects.equals(feedbacks, patient.feedbacks) && Objects.equals(orders, patient.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height, weight, feedbacks, orders);
    }
}