package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.exception.EntityExistException;
import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.persistence.repository.user.DoctorRepository;
import com.project.medicalanalize.persistence.repository.user.PatientRepository;
import com.project.medicalanalize.service.UserCrudService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserCrudServiceImpl implements UserCrudService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final CrudRepositoryHelper<Doctor, DoctorRepository> crudRepositoryHelperDoctor;
    private final CrudRepositoryHelper<Patient, PatientRepository> crudRepositoryHelperPatient;

    public UserCrudServiceImpl(
            BCryptPasswordEncoder bCryptPasswordEncoder,
            DoctorRepository doctorRepository,
            PatientRepository patientRepository,
            CrudRepositoryHelper<Doctor, DoctorRepository> crudRepositoryHelperDoctor,
            CrudRepositoryHelper<Patient, PatientRepository> crudRepositoryHelperPatient) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.crudRepositoryHelperDoctor = crudRepositoryHelperDoctor;
        this.crudRepositoryHelperPatient = crudRepositoryHelperPatient;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(User personal) {
        if (personal instanceof Doctor) {
            if (doctorRepository.existsByEmail(personal.getEmail())) {
                throw new EntityExistException("this doctor is exist");
            }
            Doctor doctor = (Doctor) personal;
            doctor.setPassword(bCryptPasswordEncoder.encode(personal.getPassword()));
            crudRepositoryHelperDoctor.create(doctorRepository, doctor);
        }

        if (personal instanceof Patient) {
            if (patientRepository.existsByEmail(personal.getEmail())) {
                throw new EntityExistException("this patient is exist");
            }
            Patient patient = (Patient) personal;
            patient.setPassword(bCryptPasswordEncoder.encode(personal.getPassword()));
            crudRepositoryHelperPatient.create(patientRepository, patient);
        }
    }

    @Override
    public void update(User entity) {
        if (entity instanceof Patient) {
            crudRepositoryHelperPatient.update(patientRepository, (Patient) entity);
        } else
            crudRepositoryHelperDoctor.update(doctorRepository, (Doctor) entity);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public DataTableResponse<User> findAll(DataTableRequest request) {
        return null;
    }
}