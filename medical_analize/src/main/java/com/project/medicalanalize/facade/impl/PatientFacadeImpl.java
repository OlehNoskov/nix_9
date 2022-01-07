package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.PatientFacade;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.service.PatientService;
import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.web.dto.request.PageAndSizeData;
import com.project.medicalanalize.web.dto.request.PatientRequestDto;
import com.project.medicalanalize.web.dto.request.SortData;
import com.project.medicalanalize.web.dto.response.PageData;
import com.project.medicalanalize.web.dto.response.PatientResponseDto;


import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientFacadeImpl implements PatientFacade {

    private final PatientService patientService;

    public PatientFacadeImpl(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public void create(PatientRequestDto patientRequestDto) {

    }

    @Override
    public void update(PatientRequestDto patientRequestDto, long id) {
        Patient patient = patientService.findById(id).get();
        patient.setId(id);
        patient.setFirstName(patientRequestDto.getFirstName());
        patient.setLastName(patientRequestDto.getLastName());
//        patient.setBirthDay(patientRequestDto.getBirthDay());
        patient.setSex(patient.getSex());
        patient.setCountry(patient.getCountry());
        patient.setHeight(patient.getHeight());
        patient.setWeight(patient.getWeight());
//        patient.setPhone(patient.getPhone());
        System.out.println("facade update patient");
        patientService.update(patient);
    }

    @Override
    public void delete(long id) {
        patientService.delete(id);
    }

    @Override
    public PatientResponseDto findById(long id) {
        System.out.println("Patient facade!");
        return new PatientResponseDto(patientService.findById(id).get());
    }

    @Override
    public PageData<PatientResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

//        Map<String, String[]> parameterMap = request.getParameterMap();

//        if (MapUtils.isNotEmpty(parameterMap)) {
//            String[] params = request.getParameterMap().get("groupId");
//        }
        DataTableResponse<Patient> all = patientService.findAll(dataTableRequest);

        List<PatientResponseDto> list = all.getItems().
                stream().
                map(PatientResponseDto::new).
                collect(Collectors.toList());

        PageData<PatientResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }
}