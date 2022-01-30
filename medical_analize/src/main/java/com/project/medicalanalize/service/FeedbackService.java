package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.feedback.Feedback;

import java.util.List;

public interface FeedbackService extends BaseCrudService<Feedback> {

    DataTableResponse<Feedback> findAllFeedbacksPatient(DataTableRequest request, Long idPatient);

    List<Feedback> findAll();
}