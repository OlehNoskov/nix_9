package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.exception.EntityExistException;
import com.project.medicalanalize.logger.LoggerLevel;
import com.project.medicalanalize.logger.LoggerService;
import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.repository.user.DoctorRepository;
import com.project.medicalanalize.service.DoctorService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CrudRepositoryHelper<Doctor, DoctorRepository> doctorRepositoryHelper;
    private final DoctorRepository doctorRepository;
    private final LoggerService loggerService;

    public DoctorServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, CrudRepositoryHelper<Doctor,
            DoctorRepository> doctorRepositoryHelper, DoctorRepository doctorRepository, LoggerService loggerService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.doctorRepositoryHelper = doctorRepositoryHelper;
        this.doctorRepository = doctorRepository;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(Doctor doctor) {
        if(doctorRepository.existsByEmail(doctor.getEmail())){
            loggerService.commit(LoggerLevel.ERROR, "Problem: This doctor is exist email!");
            throw new EntityExistException("this doctor is exist!");
        }
        doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
        loggerService.commit(LoggerLevel.INFO, "Start create doctor!");
        doctorRepositoryHelper.create(doctorRepository, doctor);
        loggerService.commit(LoggerLevel.INFO, "Finish create doctor! id=" + doctor.getId());
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Doctor doctor) {
        loggerService.commit(LoggerLevel.INFO, "Start update doctor! id=" + doctor.getId());
        doctorRepositoryHelper.update(doctorRepository, doctor);
        loggerService.commit(LoggerLevel.INFO, "Finish update doctor! id=" + doctor.getId());
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.INFO, "Start delete doctor id=" + id);
        doctorRepositoryHelper.delete(doctorRepository, id);
        loggerService.commit(LoggerLevel.INFO, "Doctor successfully removed id=" + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional findById(Long id) {
        loggerService.commit(LoggerLevel.WARN, "Start search doctor id=" + id);
        return doctorRepositoryHelper.findById(doctorRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse findAll(DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Start findAll doctors!");
        return doctorRepositoryHelper.findAll(doctorRepository, request);
    }
}