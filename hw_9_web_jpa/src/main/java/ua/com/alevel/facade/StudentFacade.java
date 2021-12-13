package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.StudentFullResponseDto;
import ua.com.alevel.view.dto.response.StudentSimpleResponseDto;

public interface StudentFacade extends BaseFacade<StudentRequestDto, StudentSimpleResponseDto, StudentFullResponseDto> {
}