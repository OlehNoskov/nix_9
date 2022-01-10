package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.exception.EntityExistException;
import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.repository.user.PatientRepository;
import com.project.medicalanalize.service.PatientService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CrudRepositoryHelper<Patient, PatientRepository> patientRepositoryHelper;
    private final PatientRepository patientRepository;

    public PatientServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                              CrudRepositoryHelper<Patient, PatientRepository> patientRepositoryHelper,
                              PatientRepository patientRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.patientRepositoryHelper = patientRepositoryHelper;
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Patient patient) {
        if(patientRepository.existsByEmail(patient.getEmail())){
            throw new EntityExistException("this patient is exist!");
        }
        patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));
        patientRepositoryHelper.create(patientRepository, patient);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Patient entity) {
    patientRepositoryHelper.update(patientRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        patientRepositoryHelper.delete(patientRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Patient> findById(Long id) {
        return patientRepositoryHelper.findById(patientRepository,id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        return patientRepositoryHelper.findAll(patientRepository,request);
    }
}