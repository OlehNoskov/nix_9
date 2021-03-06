package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;

import lombok.Getter;

public class OrderResponseDto extends ResponseDto {

    @Getter
    private String badHabits;

    @Getter
    private String drugsTaken;

    @Getter
    private String chronicDiseases;

    @Getter
    private String hereditary_diseases;

    @Getter
    private String featuresNutrition;

    @Getter
    private String answer;

    @Getter
    private Patient patient;

    @Getter
    private Doctor doctor;

    private String namePatient;

    private String nameDoctor;

    public OrderResponseDto(Order order) {
        super();
        setId(order.getId());
        this.badHabits = order.getBadHabits();
        this.drugsTaken = order.getDrugsTaken();
        this.chronicDiseases = order.getChronicDiseases();
        this.hereditary_diseases = order.getHereditary_diseases();
        this.featuresNutrition = order.getFeaturesNutrition();
        this.answer = order.getAnswer();
        this.patient = order.getPatient();
        this.doctor = order.getDoctor();
    }

    public String getNamePatient() {
        if(patient.getFirstName() == null && patient.getLastName() == null){
            return " ";
        }
        return patient.getFirstName() + " " + patient.getLastName();
    }

    public String getNameDoctor() {
        if(doctor.getFirstName() == null && doctor.getLastName() == null){
            return " ";
        }
        return doctor.getFirstName() + " " + doctor.getLastName();
    }
}