package com.project.medicalanalize.web.dto.response;

public class OrderResponseDto extends ResponseDto {

    private String badHabits;
    private String drugsTaken;
    private String chronicDiseases;
    private String burglaryComplaints;
    private String hereditary_diseases;
    private String featuresNutrition;

    public OrderResponseDto() {
        this.badHabits = getBadHabits();
        this.drugsTaken = getDrugsTaken();
        this.chronicDiseases = getChronicDiseases();
        this.burglaryComplaints = getBurglaryComplaints();
        this.hereditary_diseases = getHereditary_diseases();
        this.featuresNutrition = getFeaturesNutrition();
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
}