package com.project.medicalanalize.service.impl.typetranscript;

import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.transcript.GeneralBlood;
import com.project.medicalanalize.persistence.repository.order.transcript.GeneralBloodRepository;
import com.project.medicalanalize.service.typetranscript.GeneralBloodService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GeneralBloodServiceImpl implements GeneralBloodService {


    private final CrudRepositoryHelper<GeneralBlood, GeneralBloodRepository> generalBloodRepositoryCrudRepositoryHelper;
    private final GeneralBloodRepository generalBloodRepository;

    public GeneralBloodServiceImpl(CrudRepositoryHelper<GeneralBlood, GeneralBloodRepository> generalBloodRepositoryCrudRepositoryHelper,
                                   GeneralBloodRepository generalBloodRepository) {
        this.generalBloodRepositoryCrudRepositoryHelper = generalBloodRepositoryCrudRepositoryHelper;
        this.generalBloodRepository = generalBloodRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(GeneralBlood entity) {
        generalBloodRepositoryCrudRepositoryHelper.create(generalBloodRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(GeneralBlood entity) {
        generalBloodRepositoryCrudRepositoryHelper.update(generalBloodRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        generalBloodRepositoryCrudRepositoryHelper.delete(generalBloodRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GeneralBlood> findById(Long id) {
        return generalBloodRepositoryCrudRepositoryHelper.findById(generalBloodRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<GeneralBlood> findAll(DataTableRequest request) {
        return generalBloodRepositoryCrudRepositoryHelper.findAll(generalBloodRepository, request);
    }
}