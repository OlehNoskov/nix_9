package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.CheckUp;

public class CheckUpResponseDto extends ResponseDto{

    private Integer price;
    private String badHabits;
    private String drugsTaken;
    private String chronicDiseases;
    private String burglaryComplaints;
    private String hereditary_diseases;
    private String featuresNutrition;
    private String complaints;

    public CheckUpResponseDto(CheckUp checkUp) {
        setId(checkUp.getId());
        setCreated(checkUp.getCreated());
        setUpdated(checkUp.getUpdated());
        setVisible(checkUp.getVisible());
        this.price = checkUp.getPrice();
        this.badHabits = checkUp.getBadHabits();
        this.drugsTaken = checkUp.getDrugsTaken();
        this.chronicDiseases = checkUp.getChronicDiseases();
        this.burglaryComplaints = checkUp.getBurglaryComplaints();
        this.hereditary_diseases = checkUp.getHereditary_diseases();
        this.featuresNutrition = checkUp.getFeaturesNutrition();
        this.complaints = checkUp.getComplaints();
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
}