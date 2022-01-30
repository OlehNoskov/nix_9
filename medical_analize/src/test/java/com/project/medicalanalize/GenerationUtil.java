package com.project.medicalanalize;

import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.repository.type.RoleType;

import java.util.Date;

public final class GenerationUtil {

    public GenerationUtil() {
    }

    public static Patient generatePatient(String firstName, String lastName, String email, String password) {
        Patient patient = new Patient();
        patient.setBirthDay(new Date());
        patient.setRoleType(RoleType.ROLE_PATIENT);
        patient.setEnabled(true);
        patient.setLastName(lastName);
        patient.setFirstName(firstName);
        patient.setEmail(email);
        patient.setPassword(password);
        return patient;
    }

    public static Doctor generateDoctor(String firstName, String lastName, String email, String password) {
        Doctor doctor = new Doctor();
        doctor.setBirthDay(new Date());
        doctor.setRoleType(RoleType.ROLE_DOCTOR);
        doctor.setEnabled(true);
        doctor.setLastName(lastName);
        doctor.setFirstName(firstName);
        doctor.setEmail(email);
        doctor.setPassword(password);
        return doctor;
    }
}