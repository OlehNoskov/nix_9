package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.entity.user.Doctor;

public interface OrderService extends BaseCrudService<Order>{

    Doctor getDoctors(Long orderId);
}