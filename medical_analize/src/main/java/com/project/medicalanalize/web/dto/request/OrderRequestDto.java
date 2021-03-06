package com.project.medicalanalize.web.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderRequestDto extends RequestDto {

    private String badHabits;
    private String drugsTaken;
    private String chronicDiseases;
    private String hereditary_diseases;
    private String featuresNutrition;
    private String answer;
}