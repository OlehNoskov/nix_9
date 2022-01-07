package com.project.medicalanalize.persistence.entity.order;

import com.project.medicalanalize.persistence.entity.BaseEntity;
import com.project.medicalanalize.persistence.entity.user.Patient;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "number_order", unique = true)
    private Integer numberOrder;

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    private Patient patient;

    public Order() {
        super();
    }

    public Integer getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(Integer numberOrder) {
        this.numberOrder = numberOrder;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}