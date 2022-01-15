package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.exception.EntityExistException;
import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.repository.order.OrderRepository;
import com.project.medicalanalize.persistence.repository.order.OrderSimpleRepository;
import com.project.medicalanalize.persistence.repository.user.DoctorRepository;
import com.project.medicalanalize.service.DoctorService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CrudRepositoryHelper<Doctor, DoctorRepository> doctorRepositoryHelper;
    private final DoctorRepository doctorRepository;
    private final CrudRepositoryHelper<Order, OrderSimpleRepository> simpleRepositoryCrudRepositoryHelper;
    private final OrderSimpleRepository orderRepository;

    public DoctorServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, CrudRepositoryHelper<Doctor,
            DoctorRepository> doctorRepositoryHelper, DoctorRepository doctorRepository, CrudRepositoryHelper<Order, OrderSimpleRepository> simpleRepositoryCrudRepositoryHelper, OrderRepository orderRepository, OrderSimpleRepository orderRepository1) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.doctorRepositoryHelper = doctorRepositoryHelper;
        this.doctorRepository = doctorRepository;
        this.simpleRepositoryCrudRepositoryHelper = simpleRepositoryCrudRepositoryHelper;

        this.orderRepository = orderRepository1;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Doctor doctor) {
        if(doctorRepository.existsByEmail(doctor.getEmail())){
            throw new EntityExistException("this doctor is exist!");
        }
        doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
        doctorRepositoryHelper.create(doctorRepository, doctor);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Doctor doctor) {
        doctorRepositoryHelper.update(doctorRepository, doctor);
    }

    @Override
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
}