package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.ComprehensiveConsultationOrder;

public class ConsultationResponseDto extends ResponseDto{

    private Integer price;
    private String badHabits;
    private String drugsTaken;
    private String chronicDiseases;
    private String burglaryComplaints;
    private String hereditary_diseases;
    private String featuresNutrition;
    private String complaints;
    private String file;

    public ConsultationResponseDto(ComprehensiveConsultationOrder consultationOrder) {
        setId(consultationOrder.getId());
        setCreated(consultationOrder.getCreated());
        setUpdated(consultationOrder.getUpdated());
        setVisible(consultationOrder.getVisible());
        this.price = consultationOrder.getPrice();
        this.badHabits = consultationOrder.getBadHabits();
        this.drugsTaken = consultationOrder.getDrugsTaken();
        this.chronicDiseases = consultationOrder.getChronicDiseases();
        this.burglaryComplaints = consultationOrder.getBurglaryComplaints();
        this.hereditary_diseases = consultationOrder.getHereditary_diseases();
        this.featuresNutrition = consultationOrder.getFeaturesNutrition();
        this.complaints = consultationOrder.getComplaints();
        this.file = consultationOrder.getFile();
    }

    public Integer getPrice() {
        return price;
    }

    public String getBadHabits() {
        return badHabits;
    }

    public String getDrugsTaken() {
        return drugsTaken;
    }

    public String getChronicDiseases() {
        return chronicDiseases;
    }

    public String getBurglaryComplaints() {
        return burglaryComplaints;
    }

    public String getHereditary_diseases() {
        return hereditary_diseases;
    }

    public String getFeaturesNutrition() {
        return featuresNutrition;
    }

    public String getComplaints() {
        return complaints;
    }
    public String getFile() {
        return file;
    }
}