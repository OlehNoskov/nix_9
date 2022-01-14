package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.ComprehensiveConsultationOrder;
import com.project.medicalanalize.persistence.repository.order.ComprehensiveConsultationOrderRepository;
import com.project.medicalanalize.service.ComprehensiveConsultationOrderService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ComprehensiveConsultationOrderImpl implements ComprehensiveConsultationOrderService {

    private final CrudRepositoryHelper<ComprehensiveConsultationOrder, ComprehensiveConsultationOrderRepository> consultationRepositoryHelper;
    private final ComprehensiveConsultationOrderRepository consultationOrderRepository;

    public ComprehensiveConsultationOrderImpl(CrudRepositoryHelper<ComprehensiveConsultationOrder, ComprehensiveConsultationOrderRepository> consultationRepositoryHelper, ComprehensiveConsultationOrderRepository consultationOrderRepository) {
        this.consultationRepositoryHelper = consultationRepositoryHelper;
        this.consultationOrderRepository = consultationOrderRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(ComprehensiveConsultationOrder entity) {
        consultationRepositoryHelper.create(consultationOrderRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(ComprehensiveConsultationOrder entity) {
        consultationRepositoryHelper.update(consultationOrderRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        consultationRepositoryHelper.delete(consultationOrderRepository,id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ComprehensiveConsultationOrder> findById(Long id) {
        return consultationRepositoryHelper.findById(consultationOrderRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<ComprehensiveConsultationOrder> findAll(DataTableRequest request) {
        return consultationRepositoryHelper.findAll(consultationOrderRepository,request);
    }
}