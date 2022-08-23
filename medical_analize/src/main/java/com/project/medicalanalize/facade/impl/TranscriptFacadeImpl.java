package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.TranscriptFacade;
import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.service.TranscriptService;
import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.util.WebResponseUtil;
import com.project.medicalanalize.web.dto.request.PageAndSizeData;
import com.project.medicalanalize.web.dto.request.SortData;
import com.project.medicalanalize.web.dto.request.TranscriptRequestDto;
import com.project.medicalanalize.web.dto.response.PageData;
import com.project.medicalanalize.web.dto.response.TranscriptResponseDto;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranscriptFacadeImpl implements TranscriptFacade {

    private final TranscriptService transcriptService;
    private final UserFacade userFacade;

    public TranscriptFacadeImpl(TranscriptService transcriptService, UserFacade userFacade) {
        this.transcriptService = transcriptService;
        this.userFacade = userFacade;
    }

    @Override
    public void create(TranscriptRequestDto transcriptRequestDto) {
        TranscriptOrder transcript = new TranscriptOrder();
        setterTranscript(transcriptRequestDto, transcript);
        transcript.setPrice(TranscriptOrder.getPrice());
        transcript.setPatient((Patient) userFacade.getCurrentUser());
        transcriptService.create(transcript);
    }

    @Override
    public void update(TranscriptRequestDto transcriptRequestDto, long id) {
        TranscriptOrder transcript = transcriptService.findById(id).get();
        transcript.setUpdated(new Timestamp(System.currentTimeMillis()));
        transcript.setVisible(false);
        transcript.setDoctor((Doctor) userFacade.getCurrentUser());
        transcript.setAnswer(transcriptRequestDto.getAnswer());
        transcriptService.update(transcript);
    }

    @Override
    public void delete(long id) {
        transcriptService.delete(id);
    }

    @Override
    public TranscriptResponseDto findById(long id) {
        return new TranscriptResponseDto(transcriptService.findById(id).get());
    }

    @Override
    public PageData<TranscriptResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());
        DataTableResponse<TranscriptOrder> all = transcriptService.findAll(dataTableRequest);
        return getTranscriptResponseDtoPageData(pageAndSizeData, sortData, all);
    }

    private PageData<TranscriptResponseDto> getTranscriptResponseDtoPageData(PageAndSizeData pageAndSizeData, SortData sortData, DataTableResponse<TranscriptOrder> all) {
        List<TranscriptResponseDto> list = all.getItems().
                stream().
                map(TranscriptResponseDto::new).
                collect(Collectors.toList());
        PageData<TranscriptResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private TranscriptOrder setterTranscript(TranscriptRequestDto transcriptRequestDto, TranscriptOrder transcript) {
        transcript.setPayment(false);
        transcript.setProphylacticDoses(transcriptRequestDto.getProphylactic_doses_type());
        transcript.setBadHabits(transcriptRequestDto.getBadHabits());
        transcript.setDrugsTaken(transcriptRequestDto.getDrugsTaken());
        transcript.setChronicDiseases(transcriptRequestDto.getChronicDiseases());
        transcript.setHereditary_diseases(transcriptRequestDto.getHereditary_diseases());
        transcript.setFeaturesNutrition(transcriptRequestDto.getFeaturesNutrition());
        transcript.setComplaints(transcriptRequestDto.getComplaints());
        transcript.setAnswer(transcriptRequestDto.getAnswer());
        return transcript;
    }

    @Override
    public Long createAndFind(TranscriptRequestDto dto) {
        TranscriptOrder transcript = new TranscriptOrder();
        setterTranscript(dto, transcript);
        transcript.setPrice(TranscriptOrder.getPrice());
        transcript.setPatient((Patient) userFacade.getCurrentUser());
        return transcriptService.createAndFind(transcript);
    }

    @Override
    public PageData<TranscriptResponseDto> findAllSuccessTranscriptVisibleAdmin(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());
        DataTableResponse<TranscriptOrder> tableResponse = transcriptService.findAllSuccessTranscriptVisibleAdmin(dataTableRequest);
        return getTranscriptResponseDtoPageData(pageAndSizeData, sortData, tableResponse);
    }

    @Override
    public PageData<TranscriptResponseDto> findAllTranscriptVisibleDoctor(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<TranscriptOrder> tableResponse = transcriptService.findAllTranscriptVisibleDoctor(dataTableRequest);
        List<TranscriptResponseDto> list = tableResponse.getItems().stream().
                map(TranscriptResponseDto::new).
                collect(Collectors.toList());
        PageData<TranscriptResponseDto> pageData = (PageData<TranscriptResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(list);
        return pageData;
    }

    @Override
    public PageData<TranscriptResponseDto> findAllSuccessTranscriptPatient(WebRequest request, Long idPatient) {
        User user = userFacade.getCurrentUser();
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<TranscriptOrder> tableResponse = transcriptService.findAllSuccessTranscriptPatient(dataTableRequest, user.getId());
        List<TranscriptResponseDto> list = tableResponse.getItems().stream().
                map(TranscriptResponseDto::new).
                collect(Collectors.toList());
        PageData<TranscriptResponseDto> pageData = (PageData<TranscriptResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(list);
        return pageData;
    }

    @Override
    public void paymentStatus(long id) {
        TranscriptOrder transcript = transcriptService.findById(id).get();
        transcript.setPayment(true);
        transcriptService.update(transcript);
    }
}