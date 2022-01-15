package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.entity.user.Patient;
import lombok.Getter;

import java.util.Set;

public class PatientResponseDto extends UserResponseDto {

    @Getter
    private Integer height;

    @Getter
    private Integer weight;

    @Getter
    private Set<Order> orders;

    public PatientResponseDto(Patient patient) {
        super(patient);
        this.height = patient.getHeight();
        this.weight = patient.getWeight();
        this.orders = patient.getOrders();
    }
}