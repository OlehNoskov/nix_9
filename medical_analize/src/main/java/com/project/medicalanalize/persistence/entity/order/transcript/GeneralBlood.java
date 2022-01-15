package com.project.medicalanalize.persistence.entity.order.transcript;

import com.project.medicalanalize.persistence.entity.BaseEntity;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "general_blood")
@Data
public class GeneralBlood extends BaseEntity {

    private String hemoglobin;
    private String erythrocytes;
    private String reticulocytes;
    private String platelets;
    private String leukocytes;
    private String soe;
    private String myelocytes;
    private String metamyelocytes;
    private String stab;
    private String segmented_nuclear;
    private String eosinophils;
    private String basophils;
    private String lymphocytes;
    private String monocytes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transcript_order_id")
    private TranscriptOrder transcript;

    public GeneralBlood() {
        super();
    }
}