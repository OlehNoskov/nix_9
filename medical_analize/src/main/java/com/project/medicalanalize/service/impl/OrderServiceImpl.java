package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.Order;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.repository.order.OrderSimpleRepository;
import com.project.medicalanalize.persistence.repository.user.DoctorRepository;
import com.project.medicalanalize.service.OrderService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final CrudRepositoryHelper<Order, OrderSimpleRepository> orderRepositoryHelper;
    private final OrderSimpleRepository orderSimpleRepository;
    private final Doctor doctor;

    public OrderServiceImpl(CrudRepositoryHelper<Order, OrderSimpleRepository> orderRepositoryHelper, OrderSimpleRepository orderSimpleRepository, DoctorRepository doctorRepository, Doctor doctor) {
        this.orderRepositoryHelper = orderRepositoryHelper;
        this.orderSimpleRepository = orderSimpleRepository;
        this.doctor = doctor;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Order entity) {
        orderRepositoryHelper.create(orderSimpleRepository,entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Order entity) {
        orderRepositoryHelper.update(orderSimpleRepository,entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        orderRepositoryHelper.delete(orderSimpleRepository,id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findById(Long id) {
        return orderRepositoryHelper.findById(orderSimpleRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Order> findAll(DataTableRequest request) {
        return orderRepositoryHelper.findAll(orderSimpleRepository, request);
    }

    @Override
    public Doctor getDoctors(Long orderId) {
        return orderRepositoryHelper.findById(orderSimpleRepository, orderId).get().getDoctor();
    }
}