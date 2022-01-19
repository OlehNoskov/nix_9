package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.repository.order.CheckUpRepository;
import com.project.medicalanalize.service.CheckUpService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CheckUpServiceImpl implements CheckUpService {

    private final CrudRepositoryHelper<CheckUp, CheckUpRepository> checkUpRepositoryHelper;
    private final CheckUpRepository checkUpRepository;

    public CheckUpServiceImpl(CrudRepositoryHelper<CheckUp, CheckUpRepository> checkUpRepositoryHelper, CheckUpRepository checkUpRepository) {
        this.checkUpRepositoryHelper = checkUpRepositoryHelper;
        this.checkUpRepository = checkUpRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(CheckUp entity) {
        checkUpRepositoryHelper.create(checkUpRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(CheckUp entity) {
        checkUpRepositoryHelper.update(checkUpRepository,entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        checkUpRepositoryHelper.delete(checkUpRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CheckUp> findById(Long id) {
        return checkUpRepositoryHelper.findById(checkUpRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<CheckUp> findAll(DataTableRequest request) {
        return checkUpRepositoryHelper.findAll(checkUpRepository, request);
    }

    @Override
    public Long createAndFind(CheckUp order) {
        return checkUpRepositoryHelper.createAndFind(checkUpRepository, order);
    }
}