package com.project.medicalanalize.web.dto.request;

public class OrderRequestDto extends RequestDto{

    private Integer price;
    private String badHabits;
    private String drugsTaken;
    private String chronicDiseases;
    private String burglaryComplaints;
    private String hereditary_diseases;
    private String featuresNutrition;

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
}