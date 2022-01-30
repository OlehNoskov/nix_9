package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;

import java.util.List;

public interface TranscriptService extends BaseCrudService<TranscriptOrder> {

    Long createAndFind(TranscriptOrder order);

    DataTableResponse<TranscriptOrder> findAllSuccessTranscriptVisibleAdmin(DataTableRequest request);

    DataTableResponse<TranscriptOrder> findAllTranscriptVisibleDoctor(DataTableRequest request);

    DataTableResponse<TranscriptOrder> findAllSuccessTranscriptPatient(DataTableRequest request, Long idPatient);

    List<TranscriptOrder> findAll();
}