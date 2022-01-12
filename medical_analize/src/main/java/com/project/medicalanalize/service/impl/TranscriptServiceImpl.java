package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.Transcript;
import com.project.medicalanalize.persistence.repository.order.TranscriptRepository;
import com.project.medicalanalize.service.TranscriptService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TranscriptServiceImpl implements TranscriptService {

    private final CrudRepositoryHelper<Transcript, TranscriptRepository> transcriptRepositoryHelper;
    private final TranscriptRepository transcriptRepository;

    public TranscriptServiceImpl(CrudRepositoryHelper<Transcript, TranscriptRepository> transcriptRepositoryHelper,
                                 TranscriptRepository transcriptRepository) {
        this.transcriptRepositoryHelper = transcriptRepositoryHelper;
        this.transcriptRepository = transcriptRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Transcript entity) {
        transcriptRepositoryHelper.create(transcriptRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Transcript entity) {
        transcriptRepositoryHelper.update(transcriptRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        transcriptRepositoryHelper.delete(transcriptRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transcript> findById(Long id) {
        return transcriptRepositoryHelper.findById(transcriptRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Transcript> findAll(DataTableRequest request) {
        return transcriptRepositoryHelper.findAll(transcriptRepository, request);
    }
}