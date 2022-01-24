package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.feedback.Feedback;
import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.persistence.repository.feedback.FeedbackRepository;
import com.project.medicalanalize.service.FeedbackService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final CrudRepositoryHelper<Feedback, FeedbackRepository> feedbackRepositoryHelper;
    private final FeedbackRepository feedbackRepository;
    private final UserFacade userFacade;

    public FeedbackServiceImpl(CrudRepositoryHelper<Feedback, FeedbackRepository> feedbackRepositoryHelper,
                               FeedbackRepository feedbackRepository, UserFacade userFacade) {
        this.feedbackRepositoryHelper = feedbackRepositoryHelper;
        this.feedbackRepository = feedbackRepository;
        this.userFacade = userFacade;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Feedback entity) {
        feedbackRepositoryHelper.create(feedbackRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Feedback entity) {
        feedbackRepositoryHelper.update(feedbackRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        feedbackRepositoryHelper.delete(feedbackRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Feedback> findById(Long id) {
        return feedbackRepositoryHelper.findById(feedbackRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Feedback> findAll(DataTableRequest request) {
        return feedbackRepositoryHelper.findAll(feedbackRepository, request);
    }

    @Override
    public DataTableResponse<Feedback> findAllFeedbacksPatient(DataTableRequest request, Long idPatient) {
        User user = userFacade.getCurrentUser();
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<Feedback> entityPage = feedbackRepository.findAllFeedbacksPatient(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort), user.getId());
        return getFeedbackDataTableResponse(request, entityPage);
    }

    private DataTableResponse<Feedback> getFeedbackDataTableResponse(DataTableRequest request, Page<Feedback> entityPage) {
        DataTableResponse<Feedback> dataTableResponse = new DataTableResponse<>();
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