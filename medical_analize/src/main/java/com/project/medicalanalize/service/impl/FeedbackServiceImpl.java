package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.feedback.Feedback;
import com.project.medicalanalize.persistence.repository.feedback.FeedbackRepository;
import com.project.medicalanalize.service.FeedbackService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final CrudRepositoryHelper<Feedback, FeedbackRepository> feedbackRepositoryHelper;
    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(CrudRepositoryHelper<Feedback, FeedbackRepository> feedbackRepositoryHelper, FeedbackRepository feedbackRepository) {
        this.feedbackRepositoryHelper = feedbackRepositoryHelper;
        this.feedbackRepository = feedbackRepository;
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
}