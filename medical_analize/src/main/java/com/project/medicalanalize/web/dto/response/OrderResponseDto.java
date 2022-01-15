package com.project.medicalanalize.web.dto.response;

import lombok.Getter;

public class OrderResponseDto extends ResponseDto {

    @Getter
    private String badHabits;

    @Getter
    private String drugsTaken;

    @Getter
    private String chronicDiseases;

    @Getter
    private String burglaryComplaints;

    @Getter
    private String hereditary_diseases;

    @Getter
    private String featuresNutrition;

    public OrderResponseDto() {
        this.badHabits = getBadHabits();
        this.drugsTaken = getDrugsTaken();
        this.chronicDiseases = getChronicDiseases();
        this.burglaryComplaints = getBurglaryComplaints();
        this.hereditary_diseases = getHereditary_diseases();
        this.featuresNutrition = getFeaturesNutrition();
    }
}