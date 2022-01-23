package com.project.medicalanalize.facade;

import com.project.medicalanalize.web.dto.request.TranscriptRequestDto;
import com.project.medicalanalize.web.dto.response.PageData;
import com.project.medicalanalize.web.dto.response.TranscriptResponseDto;

import org.springframework.web.context.request.WebRequest;

public interface TranscriptFacade extends BaseFacade<TranscriptRequestDto, TranscriptResponseDto> {

    Long createAndFind(TranscriptRequestDto dto);

    PageData findAllTranscriptOrdersReviewDoctors(WebRequest request);
    PageData findAllTranscriptSuccessAdmin(WebRequest request);
    void paymentStatus(long id);
}