package com.project.medicalanalize.facade;

import com.project.medicalanalize.web.dto.response.DoctorResponseDto;

public interface OrderFacade extends BaseFacade{

    DoctorResponseDto getDoctor (Long orderId);
}