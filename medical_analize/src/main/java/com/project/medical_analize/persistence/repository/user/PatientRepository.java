package com.project.medical_analize.persistence.repository.user;

import com.project.medical_analize.persistence.entity.user.Patient;

import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends UserRepository<Patient> {
}