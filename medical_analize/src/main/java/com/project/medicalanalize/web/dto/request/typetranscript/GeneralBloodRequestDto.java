package com.project.medicalanalize.web.dto.request.typetranscript;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.web.dto.request.RequestDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GeneralBloodRequestDto extends RequestDto {

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
    private TranscriptOrder transcriptOrder;
}