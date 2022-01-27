package com.project.medicalanalize.facade;

import com.project.medicalanalize.web.dto.request.ConsultationRequestDto;
import com.project.medicalanalize.web.dto.response.ConsultationResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.springframework.web.context.request.WebRequest;

public interface ConsultationOrderFacade extends BaseFacade<ConsultationRequestDto, ConsultationResponseDto>{

    Long createAndFind(ConsultationRequestDto dto);

    PageData<ConsultationResponseDto>  findAllConsultationOrdersReviewDoctors(WebRequest request);

    PageData<ConsultationResponseDto>  findAllConsultationSuccessAdmin(WebRequest request);

    PageData<ConsultationResponseDto> findAllSuccessConsultationPatient(WebRequest request, Long idPatient);

    void paymentStatus(long id);
}