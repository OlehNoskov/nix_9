package com.project.medicalanalize.facade;

import com.project.medicalanalize.web.dto.request.CheckUpRequestDto;
import com.project.medicalanalize.web.dto.response.CheckUpResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.springframework.web.context.request.WebRequest;

public interface CheckUpFacade extends BaseFacade<CheckUpRequestDto, CheckUpResponseDto>{

    PageData findAllCheckUpOrdersReviewDoctors(WebRequest request);
    PageData findAllCheckUpAdmin(WebRequest request);
}