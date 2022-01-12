package com.project.medicalanalize.persistence.entity.order;

import com.project.medicalanalize.persistence.entity.BaseEntity;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.type.OrderType;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false)
    private OrderType orderType;

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    private Patient patient;

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    private Doctor doctor;

    public Order() {
        super();
        this.enabled = true;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}