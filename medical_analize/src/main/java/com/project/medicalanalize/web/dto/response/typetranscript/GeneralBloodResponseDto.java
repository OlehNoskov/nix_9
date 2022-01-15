package com.project.medicalanalize.web.dto.response.typetranscript;

import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.entity.order.transcript.GeneralBlood;
import com.project.medicalanalize.web.dto.response.ResponseDto;
import lombok.Getter;

public class GeneralBloodResponseDto extends ResponseDto {
    @Getter
    private String hemoglobin;
    @Getter
    private String erythrocytes;
    @Getter
    private String reticulocytes;
    @Getter
    private String platelets;
    @Getter
    private String leukocytes;
    @Getter
    private String soe;
    @Getter
    private String myelocytes;
    @Getter
    private String metamyelocytes;
    @Getter
    private String stab;
    @Getter
    private String segmented_nuclear;
    @Getter
    private String eosinophils;
    @Getter
    private String basophils;
    @Getter
    private String lymphocytes;
    @Getter
    private String monocytes;
    @Getter
    private TranscriptOrder transcriptOrder;

    public GeneralBloodResponseDto(GeneralBlood generalBlood) {
        super();
        setId(generalBlood.getId());
        this.hemoglobin = generalBlood.getHemoglobin();
        this.erythrocytes = generalBlood.getErythrocytes();
        this.reticulocytes = generalBlood.getReticulocytes();
        this.platelets = generalBlood.getPlatelets();
        this.leukocytes = generalBlood.getLeukocytes();
        this.soe = generalBlood.getSoe();
        this.myelocytes = generalBlood.getMyelocytes();
        this.metamyelocytes = generalBlood.getMetamyelocytes();
        this.stab = generalBlood.getStab();
        this.segmented_nuclear = generalBlood.getSegmented_nuclear();
        this.eosinophils = generalBlood.getEosinophils();
        this.basophils = generalBlood.getBasophils();
        this.lymphocytes = generalBlood.getLymphocytes();
        this.monocytes = generalBlood.getMonocytes();
    }
}
