package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.logger.LoggerLevel;
import com.project.medicalanalize.logger.LoggerService;
import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.persistence.repository.order.ConsultationOrderRepository;
import com.project.medicalanalize.service.ComprehensiveConsultationOrderService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ComprehensiveConsultationOrderImpl implements ComprehensiveConsultationOrderService {

    private final CrudRepositoryHelper<ConsultationOrder, ConsultationOrderRepository> consultationRepositoryHelper;
    private final ConsultationOrderRepository consultationOrderRepository;
    private final UserFacade userFacade;
    private final LoggerService loggerService;

    public ComprehensiveConsultationOrderImpl(CrudRepositoryHelper<ConsultationOrder, ConsultationOrderRepository> consultationRepositoryHelper, ConsultationOrderRepository consultationOrderRepository, UserFacade userFacade, LoggerService loggerService) {
        this.consultationRepositoryHelper = consultationRepositoryHelper;
        this.consultationOrderRepository = consultationOrderRepository;
        this.userFacade = userFacade;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(ConsultationOrder entity) {
        loggerService.commit(LoggerLevel.INFO, "Start create consultation!");
        consultationRepositoryHelper.create(consultationOrderRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "Consultation successfully created! Id=" + entity.getId());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(ConsultationOrder entity) {
        loggerService.commit(LoggerLevel.INFO, "Start update consultation! Id=" + entity.getId());
        consultationRepositoryHelper.update(consultationOrderRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "Consultation successfully updated! Id=" + entity.getId());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "Start delete consultation! Id=" + id);
        consultationRepositoryHelper.delete(consultationOrderRepository, id);
        loggerService.commit(LoggerLevel.WARN, "Consultation successfully removed! Id=" + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ConsultationOrder> findById(Long id) {
        loggerService.commit(LoggerLevel.INFO, "Start search consultation! Id=" + id);
        return consultationRepositoryHelper.findById(consultationOrderRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<ConsultationOrder> findAll(DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Start findAll consultations!");
        return consultationRepositoryHelper.findAll(consultationOrderRepository, request);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Long createAndFind(ConsultationOrder order) {
        loggerService.commit(LoggerLevel.INFO, "Start create and find consultation! Patient id=" + userFacade.getCurrentUser().getId());
        return consultationRepositoryHelper.createAndFind(consultationOrderRepository, order);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<ConsultationOrder> findAllSuccessConsultationVisibleAdmin(DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Start findAll consultations admin! Admin id=" + userFacade.getCurrentUser().getId());
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<ConsultationOrder> entityPage = consultationOrderRepository.findAllSuccessConsultationVisibleAdmin(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        return getConsultationDataTableResponse(request, entityPage);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<ConsultationOrder> findAllConsultationVisibleDoctor(DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Start findAll consultations doctor! Doctor id=" + userFacade.getCurrentUser().getId());
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<ConsultationOrder> entityPage = consultationOrderRepository.findAllConsultationVisibleDoctor(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        return getConsultationDataTableResponse(request, entityPage);
    }

    @Override
    public DataTableResponse<ConsultationOrder> findAllSuccessConsultationPatient(DataTableRequest request, Long idPatient) {
        loggerService.commit(LoggerLevel.INFO, "Start findAll personal consultations patient! Patient id=" + idPatient);
        User user = userFacade.getCurrentUser();
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<ConsultationOrder> entityPage = consultationOrderRepository.findAllSuccessConsultationPatient(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort), user.getId());
        return getConsultationDataTableResponse(request, entityPage);
    }

    private DataTableResponse<ConsultationOrder> getConsultationDataTableResponse(DataTableRequest request, Page<ConsultationOrder> entityPage) {
        DataTableResponse<ConsultationOrder> dataTableResponse = new DataTableResponse<>();
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