package com.project.medicalanalize.persistence.repository.user;

import com.project.medicalanalize.persistence.entity.user.Doctor;

import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends UserRepository<Doctor> {
    Long countDoctorByRoleTypeRoleDoctor = 0L;
}