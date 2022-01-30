package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.CheckUp;

import java.util.List;

public interface CheckUpService extends BaseCrudService<CheckUp> {

    Long createAndFind(CheckUp order);

    DataTableResponse<CheckUp> findAllSuccessTranscriptVisibleAdmin(DataTableRequest request);

    DataTableResponse<CheckUp> findAllTranscriptVisibleDoctor(DataTableRequest request);

    DataTableResponse<CheckUp> findAllSuccessCheckUpPatient(DataTableRequest request, Long idPatient);

    List<CheckUp> findAll();
}