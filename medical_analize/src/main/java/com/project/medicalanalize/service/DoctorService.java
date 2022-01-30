package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.entity.user.Doctor;

import java.util.List;

public interface DoctorService extends BaseCrudService<Doctor>{

    List<Doctor> findAll();
}