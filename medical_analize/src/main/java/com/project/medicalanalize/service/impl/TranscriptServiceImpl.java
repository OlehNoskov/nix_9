package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.persistence.repository.order.TranscriptRepository;
import com.project.medicalanalize.service.TranscriptService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TranscriptServiceImpl implements TranscriptService {

    private final CrudRepositoryHelper<TranscriptOrder, TranscriptRepository> transcriptRepositoryHelper;
    private final TranscriptRepository transcriptRepository;
    private final UserFacade userFacade;

    public TranscriptServiceImpl(CrudRepositoryHelper<TranscriptOrder, TranscriptRepository> transcriptRepositoryHelper,
                                 TranscriptRepository transcriptRepository, UserFacade userFacade) {
        this.transcriptRepositoryHelper = transcriptRepositoryHelper;
        this.transcriptRepository = transcriptRepository;
        this.userFacade = userFacade;
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

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<TranscriptOrder> findAllSuccessTranscriptVisibleAdmin(DataTableRequest request) {
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<TranscriptOrder> entityPage = transcriptRepository.findAllSuccessTranscriptVisibleAdmin(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        return getTranscriptOrderDataTableResponse(request, entityPage);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<TranscriptOrder> findAllTranscriptVisibleDoctor(DataTableRequest request) {
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<TranscriptOrder> entityPage = transcriptRepository.findAllTranscriptVisibleDoctor(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        return getTranscriptOrderDataTableResponse(request, entityPage);
    }

    @Override
    public DataTableResponse<TranscriptOrder> findAllSuccessTranscriptPatient(DataTableRequest request, Long idPatient) {
        User user = userFacade.getCurrentUser();
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<TranscriptOrder> entityPage = transcriptRepository.findAllSuccessTranscriptPatient(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort), user.getId());
        return getTranscriptOrderDataTableResponse(request, entityPage);
    }

    private DataTableResponse<TranscriptOrder> getTranscriptOrderDataTableResponse(DataTableRequest request, Page<TranscriptOrder> entityPage) {
        DataTableResponse<TranscriptOrder> dataTableResponse = new DataTableResponse<>();
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