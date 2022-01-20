package com.project.medicalanalize.facade;

import com.project.medicalanalize.web.dto.request.CheckUpRequestDto;
import com.project.medicalanalize.web.dto.response.CheckUpResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.springframework.web.context.request.WebRequest;

public interface CheckUpFacade extends BaseFacade<CheckUpRequestDto, CheckUpResponseDto>{

    Long createAndFind(CheckUpRequestDto dto);
    PageData findAllCheckUpOrdersReviewDoctors(WebRequest request);
    PageData findAllCheckUpSuccessAdmin(WebRequest request);
}