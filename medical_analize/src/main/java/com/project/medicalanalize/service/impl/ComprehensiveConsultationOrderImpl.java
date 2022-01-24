package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.facade.UserFacade;
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

    public ComprehensiveConsultationOrderImpl(CrudRepositoryHelper<ConsultationOrder, ConsultationOrderRepository> consultationRepositoryHelper, ConsultationOrderRepository consultationOrderRepository, UserFacade userFacade) {
        this.consultationRepositoryHelper = consultationRepositoryHelper;
        this.consultationOrderRepository = consultationOrderRepository;
        this.userFacade = userFacade;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(ConsultationOrder entity) {
        consultationRepositoryHelper.create(consultationOrderRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(ConsultationOrder entity) {
        consultationRepositoryHelper.update(consultationOrderRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        consultationRepositoryHelper.delete(consultationOrderRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ConsultationOrder> findById(Long id) {
        return consultationRepositoryHelper.findById(consultationOrderRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<ConsultationOrder> findAll(DataTableRequest request) {
        return consultationRepositoryHelper.findAll(consultationOrderRepository, request);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<ConsultationOrder> findAllSuccessConsultationVisibleAdmin(DataTableRequest request) {
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
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<ConsultationOrder> entityPage = consultationOrderRepository.findAllConsultationVisibleDoctor(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        return getConsultationDataTableResponse(request, entityPage);
    }

    @Override
    public DataTableResponse<ConsultationOrder> findAllSuccessConsultationPatient(DataTableRequest request, Long idPatient) {
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