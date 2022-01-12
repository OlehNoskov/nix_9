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

    @ManyToOne(fetch= FetchType.LAZY, cascade= CascadeType.ALL)
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

    public Patient getPatient() {
        return patient;
    }
}