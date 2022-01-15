package com.project.medicalanalize.persistence.entity.feedback;

import com.project.medicalanalize.persistence.entity.BaseEntity;
import com.project.medicalanalize.persistence.entity.user.Patient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "feedbacks")
public class Feedback extends BaseEntity {

    @Lob
    @Column(name = "feedback", length=512)
    private String feedback;

    @Transient
    private String namePatient;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
}