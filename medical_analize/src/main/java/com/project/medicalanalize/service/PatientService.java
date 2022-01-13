package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.entity.user.Patient;

public interface PatientService extends BaseCrudService<Patient> {

    void addOrder(Long patientId, Long orderId);

    void addFeedback(Long patientId, Long feedbackId);

//    DataTableResponse<Feedback> findAll();
//
//    DataTableResponse<Order> findAll();
}