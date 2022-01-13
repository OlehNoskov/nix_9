package com.project.medicalanalize.persistence.entity.feedback;

import com.project.medicalanalize.persistence.entity.BaseEntity;
import com.project.medicalanalize.persistence.entity.user.Patient;

import javax.persistence.*;

@Entity
@Table(name = "feedbacks")
public class Feedback extends BaseEntity {

    @Column(name = "feedback")
    private String feedback;

    @Transient
    private String namePatient;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getNamePatient() {
        return getPatient().getFirstName();
    }

    public void setNamePatient(String namePatient) {
        this.namePatient = namePatient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }
}