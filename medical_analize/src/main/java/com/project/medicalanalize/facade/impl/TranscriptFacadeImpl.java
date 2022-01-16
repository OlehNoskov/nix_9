package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.TranscriptFacade;
import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.service.TranscriptService;
import com.project.medicalanalize.util.WebRequestUtil;
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
        transcript.setPrice(transcript.getPrice());
        transcript.setPatient((Patient) userFacade.getCurrentUser());
        transcriptService.create(transcript);
    }

    @Override
    public void update(TranscriptRequestDto transcriptRequestDto, long id) {
        TranscriptOrder transcript = transcriptService.findById(id).get();
        transcript.setId(id);
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

        List<TranscriptResponseDto> list = all.getItems().
                stream().filter(t -> t.getPatient().getId().equals(userFacade.getCurrentUser().getId())).
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
        transcript.setPrice(transcriptRequestDto.getPrice());
        transcript.setBadHabits(transcriptRequestDto.getBadHabits());
        transcript.setDrugsTaken(transcriptRequestDto.getDrugsTaken());
        transcript.setChronicDiseases(transcriptRequestDto.getChronicDiseases());
        transcript.setBurglaryComplaints(transcriptRequestDto.getBurglaryComplaints());
        transcript.setHereditary_diseases(transcriptRequestDto.getHereditary_diseases());
        transcript.setFeaturesNutrition(transcriptRequestDto.getFeaturesNutrition());
        transcript.setAnswer(transcriptRequestDto.getAnswer());
        return transcript;
    }

    @Override
    public PageData findAllTranscriptOrdersReviewDoctors(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<TranscriptOrder> all = transcriptService.findAll(dataTableRequest);

        List<TranscriptResponseDto> list = all.getItems().
                stream().filter(t -> t.getVisible().equals(true)).
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

    @Override
    public PageData findAllTranscriptAdmin(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<TranscriptOrder> all = transcriptService.findAll(dataTableRequest);

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
}