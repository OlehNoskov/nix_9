package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.DoctorFacade;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.service.DoctorService;
import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.validatedate.UserDateValid;
import com.project.medicalanalize.web.dto.request.DoctorRequestDto;
import com.project.medicalanalize.web.dto.request.PageAndSizeData;
import com.project.medicalanalize.web.dto.request.SortData;
import com.project.medicalanalize.web.dto.response.DoctorResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Set;
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
        doctor.setId(id);
        doctor.setFirstName(doctorRequestDto.getFirstName());
        doctor.setLastName(doctorRequestDto.getLastName());
        doctor.setBirthDay(UserDateValid.userValidDate(doctorRequestDto));
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
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Doctor> all = doctorService.findAll(dataTableRequest);

        List<DoctorResponseDto> list = all.getItems().
                stream().
                map(DoctorResponseDto::new).
                collect(Collectors.toList());

        PageData<DoctorResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    @Override
    public Set<TranscriptOrder> findDoctorAllVisibleTranscript() {
        return doctorService.findDoctorAllVisibleTranscript();
    }

    @Override
    public Set<CheckUp> findDoctorAllVisibleCheckUp() {
        return doctorService.findDoctorAllVisibleCheckUp();
    }

    @Override
    public Set<ConsultationOrder> findDoctorAllVisibleConsultation() {
        return doctorService.findDoctorAllVisibleConsultation();
    }
}