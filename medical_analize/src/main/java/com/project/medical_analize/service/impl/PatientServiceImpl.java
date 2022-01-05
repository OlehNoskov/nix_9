package com.project.medical_analize.service.impl;

import com.project.medical_analize.persistence.crud.CrudRepositoryHelper;
import com.project.medical_analize.persistence.datatable.DataTableRequest;
import com.project.medical_analize.persistence.datatable.DataTableResponse;
import com.project.medical_analize.persistence.entity.user.Patient;
import com.project.medical_analize.persistence.repository.user.PatientRepository;
import com.project.medical_analize.service.PatientService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final CrudRepositoryHelper<Patient, PatientRepository> patientRepositoryHelper;
    private final PatientRepository patientRepository;

    public PatientServiceImpl(CrudRepositoryHelper<Patient, PatientRepository> patientRepositoryHelper,
                                                            PatientRepository patientRepository) {
        this.patientRepositoryHelper = patientRepositoryHelper;
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Patient entity) {

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
        System.out.println("patient service!");
        return patientRepositoryHelper.findById(patientRepository,id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        return patientRepositoryHelper.findAll(patientRepository,request);
    }
}