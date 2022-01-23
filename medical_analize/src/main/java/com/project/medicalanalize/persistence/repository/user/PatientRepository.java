package com.project.medicalanalize.persistence.repository.user;

import com.project.medicalanalize.persistence.entity.user.Patient;

import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends UserRepository<Patient> {
    Long countPatientByRoleTypeRolePatient = 0L;
}