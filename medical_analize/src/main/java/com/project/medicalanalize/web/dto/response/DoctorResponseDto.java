package com.project.medicalanalize.web.dto.response;

import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.entity.user.Doctor;

import java.util.Set;

public class DoctorResponseDto extends UserResponseDto {

    private Set<Order> orders;

    public DoctorResponseDto(Doctor doctor) {
        super(doctor);
        this.orders = doctor.getOrders();
    }

    public Set<Order> getOrders() {
        return orders;
    }
}