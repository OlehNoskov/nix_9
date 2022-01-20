package com.project.medicalanalize.persistence.entity.order;

import com.project.medicalanalize.persistence.entity.BaseEntity;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.type.OrderType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "orders")
@Data
public class Order extends BaseEntity {

    private Boolean enabled;

//    @Column(name = "price")
//    private Integer price;//TODO

    @Column(name = "bad_habits")
    private String badHabits;

    @Column(name = "drugs_taken")
    private String drugsTaken;

    @Column(name = "chronic_diseases")
    private String chronicDiseases;

    @Column(name = "burglary_complaints")
    private String burglaryComplaints;

    @Column(name = "hereditary_diseases")
    private String hereditary_diseases;

    @Column(name = "features_nutrition")
    private String featuresNutrition;

    @Column(name = "complaints")
    private String complaints;

    @Lob
    @Column(name = "answer", length = 1000)
    private String answer;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false)
    private OrderType orderType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    public Order() {
        super();
        this.enabled = true;
    }
}