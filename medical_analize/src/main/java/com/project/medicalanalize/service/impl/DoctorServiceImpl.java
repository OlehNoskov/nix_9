package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.exception.EntityExistException;
import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.repository.user.DoctorRepository;
import com.project.medicalanalize.service.DoctorService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CrudRepositoryHelper<Doctor, DoctorRepository> doctorRepositoryHelper;
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, CrudRepositoryHelper<Doctor,
            DoctorRepository> doctorRepositoryHelper, DoctorRepository doctorRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.doctorRepositoryHelper = doctorRepositoryHelper;
        this.doctorRepository = doctorRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(Doctor doctor) {
        if(doctorRepository.existsByEmail(doctor.getEmail())){
            throw new EntityExistException("this doctor is exist!");
        }
        doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
        doctorRepositoryHelper.create(doctorRepository, doctor);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Doctor doctor) {
        doctorRepositoryHelper.update(doctorRepository, doctor);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        doctorRepositoryHelper.delete(doctorRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional findById(Long id) {
        return doctorRepositoryHelper.findById(doctorRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse findAll(DataTableRequest request) {
        return doctorRepositoryHelper.findAll(doctorRepository, request);
    }

    @Override
    public Set<TranscriptOrder> findDoctorAllVisibleTranscript() {
        return doctorRepository.findDoctorAllVisibleTranscript();
    }

    @Override
    public Set<CheckUp> findDoctorAllVisibleCheckUp() {
        return doctorRepository.findDoctorAllVisibleCheckUp();
    }

    @Override
    public Set<ConsultationOrder> findDoctorAllVisibleConsultation() {
        return doctorRepository.findDoctorAllVisibleConsultation();
    }
}