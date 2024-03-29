package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.CheckUpFacade;
import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.service.CheckUpService;
import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.util.WebResponseUtil;
import com.project.medicalanalize.web.dto.request.CheckUpRequestDto;
import com.project.medicalanalize.web.dto.response.CheckUpResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckUpFacadeImpl implements CheckUpFacade {

    private final CheckUpService checkUpService;
    private final UserFacade userFacade;

    public CheckUpFacadeImpl(CheckUpService checkUpService, UserFacade userFacade) {
        this.checkUpService = checkUpService;
        this.userFacade = userFacade;
    }

    @Override
    public void create(CheckUpRequestDto checkUpRequestDto) {
        CheckUp checkUp = new CheckUp();
        setterFieldCheckUp(checkUpRequestDto, checkUp);
        checkUp.setPrice(CheckUp.getPrice());
        checkUp.setPatient((Patient) userFacade.getCurrentUser());
        checkUpService.create(checkUp);
    }

    @Override
    public void update(CheckUpRequestDto checkUpRequestDto, long id) {
        CheckUp checkUp = checkUpService.findById(id).get();
        checkUp.setUpdated(new Timestamp(System.currentTimeMillis()));
        checkUp.setVisible(false);
        checkUp.setDoctor((Doctor) userFacade.getCurrentUser());
        checkUp.setAnswer(checkUpRequestDto.getAnswer());
        checkUpService.update(checkUp);
    }

    @Override
    public void delete(long id) {
        checkUpService.delete(id);
    }

    @Override
    public CheckUpResponseDto findById(long id) {
        return new CheckUpResponseDto(checkUpService.findById(id).get());
    }

    @Override
    public PageData<CheckUpResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<CheckUp> all = checkUpService.findAll(dataTableRequest);
        List<CheckUpResponseDto> list = all.getItems().
                stream().
                map(CheckUpResponseDto::new).
                collect(Collectors.toList());
        PageData<CheckUpResponseDto> pageData = (PageData<CheckUpResponseDto>) WebResponseUtil.initPageData(all);
        pageData.setItems(list);
        return pageData;
    }

    private CheckUp setterFieldCheckUp(CheckUpRequestDto checkUpRequestDto, CheckUp checkUp) {
        checkUp.setBadHabits(checkUpRequestDto.getBadHabits());
        checkUp.setDrugsTaken(checkUpRequestDto.getDrugsTaken());
        checkUp.setChronicDiseases(checkUpRequestDto.getChronicDiseases());
        checkUp.setHereditary_diseases(checkUpRequestDto.getHereditary_diseases());
        checkUp.setFeaturesNutrition(checkUpRequestDto.getFeaturesNutrition());
        checkUp.setComplaints(checkUpRequestDto.getComplaints());
        checkUp.setAnswer(checkUpRequestDto.getAnswer());
        return checkUp;
    }

    @Override
    public Long createAndFind(CheckUpRequestDto dto) {
        CheckUp checkUp = new CheckUp();
        setterFieldCheckUp(dto, checkUp);
        checkUp.setPrice(CheckUp.getPrice());
        checkUp.setPatient((Patient) userFacade.getCurrentUser());
        return checkUpService.createAndFind(checkUp);
    }

    @Override
    public PageData findAllCheckUpOrdersReviewDoctors(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<CheckUp> tableResponse = checkUpService.findAllTranscriptVisibleDoctor(dataTableRequest);
        List<CheckUpResponseDto> list = tableResponse.getItems().stream().
                map(CheckUpResponseDto::new).
                collect(Collectors.toList());
        PageData<CheckUpResponseDto> pageData = (PageData<CheckUpResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(list);
        return pageData;
    }

    @Override
    public PageData<CheckUpResponseDto> findAllSuccessCheckUpPatient(WebRequest request, Long idPatient) {
        User user = userFacade.getCurrentUser();
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<CheckUp> tableResponse = checkUpService.findAllSuccessCheckUpPatient(dataTableRequest, user.getId());
        List<CheckUpResponseDto> list = tableResponse.getItems().stream().
                map(CheckUpResponseDto::new).
                collect(Collectors.toList());
        PageData<CheckUpResponseDto> pageData = (PageData<CheckUpResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(list);
        return pageData;
    }

    @Override
    public PageData findAllCheckUpSuccessAdmin(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<CheckUp> tableResponse = checkUpService.findAllSuccessTranscriptVisibleAdmin(dataTableRequest);
        List<CheckUpResponseDto> list = tableResponse.getItems().stream().
                map(CheckUpResponseDto::new).
                collect(Collectors.toList());
        PageData<CheckUpResponseDto> pageData = (PageData<CheckUpResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(list);
        return pageData;
    }

    @Override
    public void paymentStatus(long id) {
        CheckUp checkUp = checkUpService.findById(id).get();
        checkUp.setPayment(true);
        checkUpService.update(checkUp);
    }
}