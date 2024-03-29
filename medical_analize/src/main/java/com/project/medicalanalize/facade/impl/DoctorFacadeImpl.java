package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.DoctorFacade;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.service.DoctorService;
import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.util.WebResponseUtil;
import com.project.medicalanalize.validatedate.UserDateValid;
import com.project.medicalanalize.web.dto.request.DoctorRequestDto;
import com.project.medicalanalize.web.dto.response.DoctorResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorFacadeImpl implements DoctorFacade {

    private final DoctorService doctorService;

    public DoctorFacadeImpl(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public void create(DoctorRequestDto doctorRequestDto) {
    }

    @Override
    public void update(DoctorRequestDto doctorRequestDto, long id) {
        Doctor doctor = doctorService.findById(id).get();
        doctor.setFirstName(doctorRequestDto.getFirstName());
        doctor.setLastName(doctorRequestDto.getLastName());
        if (UserDateValid.userValidDate(doctorRequestDto) != null) {
            doctor.setBirthDay(UserDateValid.userValidDate(doctorRequestDto));
        }
        doctor.setSex(doctorRequestDto.getSex());
        doctor.setCountry(doctorRequestDto.getCountry());
        doctor.setPhone(doctorRequestDto.getPhone());
        doctor.setOrders(doctorRequestDto.getOrders());
        doctorService.update(doctor);
    }

    @Override
    public void delete(long id) {
        doctorService.delete(id);
    }

    @Override
    public DoctorResponseDto findById(long id) {
        return new DoctorResponseDto(doctorService.findById(id).get());
    }

    @Override
    public PageData<DoctorResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Doctor> all = doctorService.findAll(dataTableRequest);
        List<DoctorResponseDto> list = all.getItems().
                stream().
                map(DoctorResponseDto::new).
                collect(Collectors.toList());
        PageData<DoctorResponseDto> pageData = (PageData<DoctorResponseDto>) WebResponseUtil.initPageData(all);
        pageData.setItems(list);
        return pageData;
    }
}