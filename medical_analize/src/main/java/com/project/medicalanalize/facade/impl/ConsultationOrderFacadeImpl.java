package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.ConsultationOrderFacade;
import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.ConsultationOrder;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.service.ComprehensiveConsultationOrderService;
import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.web.dto.request.ConsultationRequestDto;
import com.project.medicalanalize.web.dto.request.PageAndSizeData;
import com.project.medicalanalize.web.dto.request.SortData;
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
        consultationOrder.setPrice(consultationOrder.getPrice());
        consultationOrder.setPatient((Patient) userFacade.getCurrentUser());
        consultationOrderService.create(consultationOrder);
    }

    @Override
    public void update(ConsultationRequestDto consultationRequestDto, long id) {
        ConsultationOrder consultationOrder = consultationOrderService.findById(id).get();
        consultationOrder.setId(id);
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

        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<ConsultationOrder> all = consultationOrderService.findAll(dataTableRequest);

        List<ConsultationResponseDto> list = all.getItems().
                stream().filter(consultation -> consultation.getPatient().getId().equals(userFacade.getCurrentUser().getId())).
                map(ConsultationResponseDto::new).
                collect(Collectors.toList());

        PageData<ConsultationResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private ConsultationOrder setterConsultation(ConsultationRequestDto consultationRequestDto, ConsultationOrder consultationOrder) {
        consultationOrder.setPrice(consultationRequestDto.getPrice());
        consultationOrder.setBadHabits(consultationRequestDto.getBadHabits());
        consultationOrder.setDrugsTaken(consultationRequestDto.getDrugsTaken());
        consultationOrder.setChronicDiseases(consultationRequestDto.getChronicDiseases());
        consultationOrder.setBurglaryComplaints(consultationRequestDto.getBurglaryComplaints());
        consultationOrder.setHereditary_diseases(consultationRequestDto.getHereditary_diseases());
        consultationOrder.setFeaturesNutrition(consultationRequestDto.getFeaturesNutrition());
        consultationOrder.setComplaints(consultationRequestDto.getComplaints());
        return consultationOrder;
    }

    @Override
    public PageData findAllConsultationOrdersReviewDoctors(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<ConsultationOrder> all = consultationOrderService.findAll(dataTableRequest);

        List<ConsultationResponseDto> list = all.getItems().
                stream().filter(t -> t.getVisible().equals(true)).
                map(ConsultationResponseDto::new).
                collect(Collectors.toList());

        PageData<ConsultationResponseDto> pageData = new PageData<>();
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
    public PageData findAllConsultationAdmin(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<ConsultationOrder> all = consultationOrderService.findAll(dataTableRequest);

        List<ConsultationResponseDto> list = all.getItems().
                stream().
                map(ConsultationResponseDto::new).
                collect(Collectors.toList());

        PageData<ConsultationResponseDto> pageData = new PageData<>();
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