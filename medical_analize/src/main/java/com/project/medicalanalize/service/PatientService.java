package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.entity.user.Patient;

import java.util.List;

public interface PatientService extends BaseCrudService<Patient> {

    List<Patient> findAll();
}