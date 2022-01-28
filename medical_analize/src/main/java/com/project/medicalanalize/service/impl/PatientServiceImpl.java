package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.exception.EntityExistException;
import com.project.medicalanalize.logger.LoggerLevel;
import com.project.medicalanalize.logger.LoggerService;
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
    private final LoggerService loggerService;

    public PatientServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                              CrudRepositoryHelper<Patient, PatientRepository> patientRepositoryHelper,
                              PatientRepository patientRepository, LoggerService loggerService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.patientRepositoryHelper = patientRepositoryHelper;
        this.patientRepository = patientRepository;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Patient patient) {
        if (patientRepository.existsByEmail(patient.getEmail())) {
            loggerService.commit(LoggerLevel.ERROR, "Problem: This patient is exist email!");
            throw new EntityExistException("this patient is exist!");
        }
        patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));
        loggerService.commit(LoggerLevel.INFO, "Start create patient!");
        patientRepositoryHelper.create(patientRepository, patient);
        loggerService.commit(LoggerLevel.INFO, "Finish create patient! Id=" + patient.getId());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Patient entity) {
        loggerService.commit(LoggerLevel.INFO, "Start update patient! Id=" + entity.getId());
        patientRepositoryHelper.update(patientRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "Finish update patient! Id=" + entity.getId());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.INFO, "Start delete patient! Id=" + id);
        patientRepositoryHelper.delete(patientRepository, id);
        loggerService.commit(LoggerLevel.INFO, "Patient successfully removed! Id=" + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Patient> findById(Long id) {
        loggerService.commit(LoggerLevel.WARN, "Start search patient! Id=" + id);
        return patientRepositoryHelper.findById(patientRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Start findAll patients!");
        return patientRepositoryHelper.findAll(patientRepository, request);
    }
}