package com.project.medicalanalize.facade;

import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.web.dto.request.DoctorRequestDto;
import com.project.medicalanalize.web.dto.response.DoctorResponseDto;

import java.util.Set;

public interface DoctorFacade extends BaseFacade<DoctorRequestDto, DoctorResponseDto> {

    Set<TranscriptOrder> findDoctorAllVisibleTranscript();

    Set<CheckUp> findDoctorAllVisibleCheckUp();

    Set<ConsultationOrder> findDoctorAllVisibleConsultation();
}