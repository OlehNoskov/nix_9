package com.project.medicalanalize.facade;

import com.project.medicalanalize.web.dto.request.CheckUpRequestDto;
import com.project.medicalanalize.web.dto.response.CheckUpResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.springframework.web.context.request.WebRequest;

public interface CheckUpFacade extends BaseFacade<CheckUpRequestDto, CheckUpResponseDto>{

    Long createAndFind(CheckUpRequestDto dto);

    PageData<CheckUpResponseDto> findAllCheckUpSuccessAdmin(WebRequest request);

    PageData<CheckUpResponseDto> findAllCheckUpOrdersReviewDoctors(WebRequest request);

    PageData<CheckUpResponseDto> findAllSuccessCheckUpPatient(WebRequest request, Long idPatient);
}