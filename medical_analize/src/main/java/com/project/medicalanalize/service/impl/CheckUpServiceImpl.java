package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.logger.LoggerLevel;
import com.project.medicalanalize.logger.LoggerService;
import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.persistence.repository.order.CheckUpRepository;
import com.project.medicalanalize.service.CheckUpService;

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
public class CheckUpServiceImpl implements CheckUpService {

    private final CrudRepositoryHelper<CheckUp, CheckUpRepository> checkUpRepositoryHelper;
    private final CheckUpRepository checkUpRepository;
    private final UserFacade userFacade;
    private final LoggerService loggerService;

    public CheckUpServiceImpl(CrudRepositoryHelper<CheckUp, CheckUpRepository> checkUpRepositoryHelper, CheckUpRepository checkUpRepository, UserFacade userFacade, LoggerService loggerService) {
        this.checkUpRepositoryHelper = checkUpRepositoryHelper;
        this.checkUpRepository = checkUpRepository;
        this.userFacade = userFacade;
        this.loggerService = loggerService;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(CheckUp entity) {
        loggerService.commit(LoggerLevel.INFO, "Start create new check-up!");
        checkUpRepositoryHelper.create(checkUpRepository, entity);
        loggerService.commit(LoggerLevel.INFO, "Check-Up successfully created! Id=" + entity.getId());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(CheckUp entity) {
        loggerService.commit(LoggerLevel.INFO, "Start update check-up! Id=" + entity.getId());
        checkUpRepositoryHelper.update(checkUpRepository,entity);
        loggerService.commit(LoggerLevel.INFO, "Check-Up successfully updated! Id=" + entity.getId());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        loggerService.commit(LoggerLevel.WARN, "Start delete check-up! Id=" + id);
        checkUpRepositoryHelper.delete(checkUpRepository, id);
        loggerService.commit(LoggerLevel.WARN, "Check-Up successfully removed! Id=" + id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CheckUp> findById(Long id) {
        loggerService.commit(LoggerLevel.WARN, "Start search check-up! Id=" + id);
        return checkUpRepositoryHelper.findById(checkUpRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<CheckUp> findAll(DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Start findAll check-up!");
        return checkUpRepositoryHelper.findAll(checkUpRepository, request);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Long createAndFind(CheckUp order) {
        loggerService.commit(LoggerLevel.INFO, "Start create and find check-up! Patient id=" + userFacade.getCurrentUser().getId());
        return checkUpRepositoryHelper.createAndFind(checkUpRepository, order);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<CheckUp> findAllSuccessTranscriptVisibleAdmin(DataTableRequest request) {
        loggerService.commit(LoggerLevel.INFO, "Start findAll check-up admin! Admin id=" + userFacade.getCurrentUser().getId());
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
        loggerService.commit(LoggerLevel.INFO, "Start findAll check-up doctor! Doctor id=" + userFacade.getCurrentUser().getId());
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<CheckUp> entityPage = checkUpRepository.findAllCheckUpVisibleDoctor(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        return getCheckUpDataTableResponse(request, entityPage);
    }

    @Override
    public DataTableResponse<CheckUp> findAllSuccessCheckUpPatient(DataTableRequest request, Long idPatient) {
        loggerService.commit(LoggerLevel.INFO, "Start findAll personal check up patient! Patient id=" + idPatient);
        User user = userFacade.getCurrentUser();
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<CheckUp> entityPage = checkUpRepository.findAllSuccessCheckUpPatient(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort), user.getId());
        return getCheckUpDataTableResponse(request, entityPage);
    }

    @Override
    public List<CheckUp> findAll() {
        loggerService.commit(LoggerLevel.INFO, "Start findAll check-up!");
        return checkUpRepository.findAll();
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