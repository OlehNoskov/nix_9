package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.repository.order.TranscriptRepository;
import com.project.medicalanalize.service.TranscriptService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TranscriptServiceImpl implements TranscriptService {

    private final CrudRepositoryHelper<TranscriptOrder, TranscriptRepository> transcriptRepositoryHelper;
    private final TranscriptRepository transcriptRepository;

    public TranscriptServiceImpl(CrudRepositoryHelper<TranscriptOrder, TranscriptRepository> transcriptRepositoryHelper,
                                 TranscriptRepository transcriptRepository) {
        this.transcriptRepositoryHelper = transcriptRepositoryHelper;
        this.transcriptRepository = transcriptRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(TranscriptOrder entity) {
        transcriptRepositoryHelper.create(transcriptRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(TranscriptOrder entity) {
        transcriptRepositoryHelper.update(transcriptRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        transcriptRepositoryHelper.delete(transcriptRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TranscriptOrder> findById(Long id) {
        return transcriptRepositoryHelper.findById(transcriptRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<TranscriptOrder> findAll(DataTableRequest request) {
        return transcriptRepositoryHelper.findAll(transcriptRepository, request);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Long createAndFind(TranscriptOrder order) {
        return transcriptRepositoryHelper.createAndFind(transcriptRepository, order);
    }
}