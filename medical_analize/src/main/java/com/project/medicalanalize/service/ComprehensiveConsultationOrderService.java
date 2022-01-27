package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;

public interface ComprehensiveConsultationOrderService extends BaseCrudService<ConsultationOrder>{

    Long createAndFind(ConsultationOrder order);

    DataTableResponse<ConsultationOrder> findAllSuccessConsultationVisibleAdmin(DataTableRequest request);

    DataTableResponse<ConsultationOrder> findAllConsultationVisibleDoctor(DataTableRequest request);

    DataTableResponse<ConsultationOrder> findAllSuccessConsultationPatient(DataTableRequest request, Long idPatient);
}