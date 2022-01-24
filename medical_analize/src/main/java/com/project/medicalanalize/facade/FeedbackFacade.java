package com.project.medicalanalize.facade;

import com.project.medicalanalize.web.dto.request.FeedbackRequestDto;
import com.project.medicalanalize.web.dto.response.FeedbackResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.springframework.web.context.request.WebRequest;

public interface FeedbackFacade extends BaseFacade<FeedbackRequestDto, FeedbackResponseDto>{

    PageData<FeedbackResponseDto> findAllFeedbackPatient(WebRequest request, Long idPatient);
}