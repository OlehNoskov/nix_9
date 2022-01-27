package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.PatientFacade;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.service.PatientService;
import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.util.WebResponseUtil;
import com.project.medicalanalize.validatedate.UserDateValid;
import com.project.medicalanalize.web.dto.request.PatientRequestDto;
import com.project.medicalanalize.web.dto.response.PageData;
import com.project.medicalanalize.web.dto.response.PatientResponseDto;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.text.ParseException;
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
    public void update(PatientRequestDto patientRequestDto, long id) throws ParseException {

        Patient patient = patientService.findById(id).get();
        patient.setId(id);
        patient.setFirstName(patientRequestDto.getFirstName());
        patient.setLastName(patientRequestDto.getLastName());
        if (UserDateValid.userValidDate(patientRequestDto) != null) {
            patient.setBirthDay(UserDateValid.userValidDate(patientRequestDto));
        }
        patient.setSex(patientRequestDto.getSex());
        patient.setCountry(patientRequestDto.getCountry());
        patient.setHeight(patientRequestDto.getHeight());
        patient.setWeight(patientRequestDto.getWeight());
        patient.setPhone(patientRequestDto.getPhone());
        patientService.update(patient);
    }

    @Override
    public void delete(long id) {
        patientService.delete(id);
    }

    @Override
    public PatientResponseDto findById(long id) {
        return new PatientResponseDto(patientService.findById(id).get());
    }

    @Override
    public PageData<PatientResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Patient> all = patientService.findAll(dataTableRequest);
        List<PatientResponseDto> list = all.getItems().
                stream().
                map(PatientResponseDto::new).
                collect(Collectors.toList());
        PageData<PatientResponseDto> pageData = (PageData<PatientResponseDto>) WebResponseUtil.initPageData(all);
        pageData.setItems(list);
        return pageData;
    }
}