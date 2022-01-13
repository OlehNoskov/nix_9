package com.project.medicalanalize.facade;

import com.project.medicalanalize.web.dto.request.LinkRequestDto;
import com.project.medicalanalize.web.dto.request.PatientRequestDto;
import com.project.medicalanalize.web.dto.response.PatientResponseDto;

public interface PatientFacade extends BaseFacade<PatientRequestDto, PatientResponseDto>{

    void addOrder(LinkRequestDto linkRequestDto);

    void addFeedback(LinkRequestDto linkRequestDto);
}