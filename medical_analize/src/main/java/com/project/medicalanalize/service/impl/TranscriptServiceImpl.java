package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.logger.LoggerLevel;
import com.project.medicalanalize.logger.LoggerService;
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

import java.util.List;
import java.util.Optional;

@Service
public class TranscriptServiceImpl implements TranscriptService {

    private final CrudRepositoryHelper<TranscriptOrder, TranscriptRepository> transcriptRepositoryHelper;
    private final TranscriptRepository transcriptRepository;
    private final UserFacade userFacade;
    private final LoggerService loggerService;

    public TranscriptServiceImpl(CrudRepositoryHelper<TranscriptOrder, TranscriptRepository> transcriptRepositoryHelper,
                                 TranscriptRepository transcriptRepository, UserFacade userFacade, LoggerService loggerService) {
        this.transcriptRepositoryHelper = transcriptRepositoryHelper;
        this.transcriptRepository = transcriptRepository;
        this.userFacade = userFacade;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(TranscriptOrder entity) {
        loggerService.commit(LoggerLevel.INFO, "Start create transcript!");
        transcriptRepositoryHelper.create(transcriptRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "Transcript successfully created! Id=" + entity.getId());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(TranscriptOrder entity) {
        loggerService.commit(LoggerLevel.INFO, "Start update transcript! Id=" + entity.getId());
        transcriptRepositoryHelper.update(transcriptRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "Transcript successfully updated! Id=" + entity.getId());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "Start delete transcript! Id=" + id);
        transcriptRepositoryHelper.delete(transcriptRepository, id);
        loggerService.commit(LoggerLevel.WARN, "Transcript successfully removed! Id=" + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TranscriptOrder> findById(Long id) {
        loggerService.commit(LoggerLevel.INFO, "Start search transcript! Id=" + id);
        return transcriptRepositoryHelper.findById(transcriptRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<TranscriptOrder> findAll(DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Start findAll transcripts!");
        return transcriptRepositoryHelper.findAll(transcriptRepository, request);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Long createAndFind(TranscriptOrder order) {
        loggerService.commit(LoggerLevel.INFO, "Start create and find transcript! Patient id=" + userFacade.getCurrentUser().getId());
        return transcriptRepositoryHelper.createAndFind(transcriptRepository, order);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<TranscriptOrder> findAllSuccessTranscriptVisibleAdmin(DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Start findAll transcripts admin! Admin id=" + userFacade.getCurrentUser().getId());
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
        loggerService.commit(LoggerLevel.INFO, "Start findAll transcripts doctor! Doctor id=" + userFacade.getCurrentUser().getId());
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<TranscriptOrder> entityPage = transcriptRepository.findAllTranscriptVisibleDoctor(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        return getTranscriptOrderDataTableResponse(request, entityPage);
    }

    @Override
    public DataTableResponse<TranscriptOrder> findAllSuccessTranscriptPatient(DataTableRequest request, Long idPatient) {
        loggerService.commit(LoggerLevel.INFO, "Start findAll personal transcripts patient! Patient id=" + idPatient);
        User user = userFacade.getCurrentUser();
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<TranscriptOrder> entityPage = transcriptRepository.findAllSuccessTranscriptPatient(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort), user.getId());
        return getTranscriptOrderDataTableResponse(request, entityPage);
    }

    @Override
    public List<TranscriptOrder> findAll() {
        loggerService.commit(LoggerLevel.INFO, "Start findAll transcripts!");
        return transcriptRepository.findAll();
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