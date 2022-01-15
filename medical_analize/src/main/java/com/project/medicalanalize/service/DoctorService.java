package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.entity.user.Doctor;

public interface DoctorService extends BaseCrudService<Doctor>{

    void addOrder(Long doctorId, Long orderId);

    void removeOrder(Long doctorId, Long orderId);
}