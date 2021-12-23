package com.project.medical_analize.persistence.repository.user;

import com.project.medical_analize.persistence.entity.user.Doctor;

import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends UserRepository<Doctor> {
}