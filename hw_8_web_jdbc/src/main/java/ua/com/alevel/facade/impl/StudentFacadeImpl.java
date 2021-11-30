package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;

@Service
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService service;

    public StudentFacadeImpl(StudentService service) {
        this.service = service;
    }

    @Override
    public void create(StudentRequestDto studentRequestDto) {

    }

    @Override
    public void update(StudentRequestDto studentRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public StudentResponseDto findById(Long id) {
        return null;
    }

    @Override
    public PageData<StudentResponseDto> findAll(WebRequest request) {
        return null;
    }
}