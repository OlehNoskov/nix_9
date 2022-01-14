package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.entity.user.Patient;

import java.util.Set;

public class PatientResponseDto extends UserResponseDto {

    private Integer height;
    private Integer weight;
    private Set<Order> orders;

    public PatientResponseDto(Patient patient) {
        super(patient);
        this.height = patient.getHeight();
        this.weight = patient.getWeight();
        this.orders = patient.getOrders();
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Set<Order> getOrders() {
        return orders;
    }
}