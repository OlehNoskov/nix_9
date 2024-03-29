package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.ConsultationOrderFacade;
import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.service.ComprehensiveConsultationOrderService;
import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.util.WebResponseUtil;
import com.project.medicalanalize.web.dto.request.ConsultationRequestDto;
import com.project.medicalanalize.web.dto.response.ConsultationResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultationOrderFacadeImpl implements ConsultationOrderFacade {

    private final ComprehensiveConsultationOrderService consultationOrderService;
    private final UserFacade userFacade;

    public ConsultationOrderFacadeImpl(ComprehensiveConsultationOrderService consultationOrderService, UserFacade userFacade) {
        this.consultationOrderService = consultationOrderService;
        this.userFacade = userFacade;
    }

    @Override
    public void create(ConsultationRequestDto consultationRequestDto) {
        ConsultationOrder consultationOrder = new ConsultationOrder();
        setterConsultation(consultationRequestDto, consultationOrder);
        consultationOrder.setPrice(ConsultationOrder.getPrice());
        consultationOrder.setPatient((Patient) userFacade.getCurrentUser());
        consultationOrderService.create(consultationOrder);
    }

    @Override
    public void update(ConsultationRequestDto consultationRequestDto, long id) {
        ConsultationOrder consultationOrder = consultationOrderService.findById(id).get();
        consultationOrder.setUpdated(new Timestamp(System.currentTimeMillis()));
        consultationOrder.setVisible(false);
        consultationOrder.setDoctor((Doctor) userFacade.getCurrentUser());
        consultationOrder.setAnswer(consultationRequestDto.getAnswer());
        consultationOrder.setMedicines(consultationRequestDto.getMedicines());
        consultationOrder.setProfileDoctor(consultationRequestDto.getProfileDoctor());
        consultationOrder.setInstrumentalResearch(consultationRequestDto.getInstrumentalResearch());
        consultationOrder.setNutritionalAdvice(consultationRequestDto.getNutritionalAdvice());
        consultationOrderService.update(consultationOrder);
    }

    @Override
    public void delete(long id) {
        consultationOrderService.delete(id);
    }

    @Override
    public ConsultationResponseDto findById(long id) {
        return new ConsultationResponseDto(consultationOrderService.findById(id).get());
    }

    @Override
    public PageData<ConsultationResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<ConsultationOrder> all = consultationOrderService.findAll(dataTableRequest);
        List<ConsultationResponseDto> list = all.getItems().
                stream().
                map(ConsultationResponseDto::new).
                collect(Collectors.toList());
        PageData<ConsultationResponseDto> pageData = (PageData<ConsultationResponseDto>) WebResponseUtil.initPageData(all);
        pageData.setItems(list);
        return pageData;
    }

    private ConsultationOrder setterConsultation(ConsultationRequestDto consultationRequestDto, ConsultationOrder consultationOrder) {
        consultationOrder.setPrice(consultationRequestDto.getPrice());
        consultationOrder.setBadHabits(consultationRequestDto.getBadHabits());
        consultationOrder.setDrugsTaken(consultationRequestDto.getDrugsTaken());
        consultationOrder.setChronicDiseases(consultationRequestDto.getChronicDiseases());
        consultationOrder.setHereditary_diseases(consultationRequestDto.getHereditary_diseases());
        consultationOrder.setFeaturesNutrition(consultationRequestDto.getFeaturesNutrition());
        consultationOrder.setComplaints(consultationRequestDto.getComplaints());
        return consultationOrder;
    }

    @Override
    public Long createAndFind(ConsultationRequestDto dto) {
        ConsultationOrder consultationOrder = new ConsultationOrder();
        setterConsultation(dto, consultationOrder);
        consultationOrder.setPrice(ConsultationOrder.getPrice());
        consultationOrder.setPatient((Patient) userFacade.getCurrentUser());
        return consultationOrderService.createAndFind(consultationOrder);
    }

    @Override
    public PageData<ConsultationResponseDto> findAllConsultationOrdersReviewDoctors(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<ConsultationOrder> tableResponse = consultationOrderService.findAllConsultationVisibleDoctor(dataTableRequest);
        List<ConsultationResponseDto> list = tableResponse.getItems().stream().
                map(ConsultationResponseDto::new).
                collect(Collectors.toList());
        PageData<ConsultationResponseDto> pageData = (PageData<ConsultationResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(list);
        return pageData;
    }

    @Override
    public PageData<ConsultationResponseDto> findAllConsultationSuccessAdmin(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<ConsultationOrder> tableResponse = consultationOrderService.findAllSuccessConsultationVisibleAdmin(dataTableRequest);
        List<ConsultationResponseDto> list = tableResponse.getItems().stream().
                map(ConsultationResponseDto::new).
                collect(Collectors.toList());
        PageData<ConsultationResponseDto> pageData = (PageData<ConsultationResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(list);
        return pageData;
    }

    @Override
    public PageData<ConsultationResponseDto> findAllSuccessConsultationPatient(WebRequest request, Long idPatient) {
        User user = userFacade.getCurrentUser();
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<ConsultationOrder> tableResponse = consultationOrderService.findAllSuccessConsultationPatient(dataTableRequest, user.getId());
        List<ConsultationResponseDto> list = tableResponse.getItems().stream().
                map(ConsultationResponseDto::new).
                collect(Collectors.toList());
        PageData<ConsultationResponseDto> pageData = (PageData<ConsultationResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(list);
        return pageData;
    }

    @Override
    public void paymentStatus(long id) {
        ConsultationOrder consultationOrder = consultationOrderService.findById(id).get();
        consultationOrder.setPayment(true);
        consultationOrderService.update(consultationOrder);
    }
}