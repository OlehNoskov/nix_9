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

    @Column(name = "link_file")
    private String file;

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

    public String getBadHabits() {
        return badHabits;
    }

    public void setBadHabits(String badHabits) {
        this.badHabits = badHabits;
    }

    public String getDrugsTaken() {
        return drugsTaken;
    }

    public void setDrugsTaken(String drugsTaken) {
        this.drugsTaken = drugsTaken;
    }

    public String getChronicDiseases() {
        return chronicDiseases;
    }

    public void setChronicDiseases(String chronicDiseases) {
        this.chronicDiseases = chronicDiseases;
    }

    public String getBurglaryComplaints() {
        return burglaryComplaints;
    }

    public void setBurglaryComplaints(String burglaryComplaints) {
        this.burglaryComplaints = burglaryComplaints;
    }

    public String getHereditary_diseases() {
        return hereditary_diseases;
    }

    public void setHereditary_diseases(String hereditary_diseases) {
        this.hereditary_diseases = hereditary_diseases;
    }

    public String getFeaturesNutrition() {
        return featuresNutrition;
    }

    public void setFeaturesNutrition(String featuresNutrition) {
        this.featuresNutrition = featuresNutrition;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}