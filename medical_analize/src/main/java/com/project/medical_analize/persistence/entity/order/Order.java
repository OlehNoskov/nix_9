package com.project.medical_analize.persistence.entity.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.medical_analize.persistence.entity.BaseEntity;
import com.project.medical_analize.persistence.entity.patient.Patient;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "number_order", unique = true)
    private Integer numberOrder;

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
    @JsonBackReference
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