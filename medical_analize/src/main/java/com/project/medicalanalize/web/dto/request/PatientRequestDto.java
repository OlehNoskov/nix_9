package com.project.medicalanalize.web.dto.request;

public class PatientRequestDto extends UserRequestDto {

    private Integer height;
    private Integer weight;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}