package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.repository.order.CheckUpRepository;
import com.project.medicalanalize.service.CheckUpService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<CheckUp> findAllSuccessTranscriptVisibleAdmin(DataTableRequest request) {
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<CheckUp> entityPage = checkUpRepository.findAllSuccessCheckUpVisibleAdmin(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        return getCheckUpDataTableResponse(request, entityPage);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<CheckUp> findAllTranscriptVisibleDoctor(DataTableRequest request) {
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<CheckUp> entityPage = checkUpRepository.findAllCheckUpVisibleDoctor(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        return getCheckUpDataTableResponse(request, entityPage);
    }

    private DataTableResponse<CheckUp> getCheckUpDataTableResponse(DataTableRequest request, Page<CheckUp> entityPage) {
        DataTableResponse<CheckUp> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setCurrentPage(request.getPage());
        dataTableResponse.setPageSize(request.getSize());
        dataTableResponse.setOrder(request.getOrder());
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setItemsSize(entityPage.getTotalElements());
        dataTableResponse.setTotalPages(entityPage.getTotalPages());
        dataTableResponse.setItems(entityPage.getContent());
        return dataTableResponse;
    }
}