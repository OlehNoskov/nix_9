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
        this.file = consultationOrder.getFile();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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