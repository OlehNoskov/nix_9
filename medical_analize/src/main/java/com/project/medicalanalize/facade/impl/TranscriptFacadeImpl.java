package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.TranscriptFacade;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.Transcript;
import com.project.medicalanalize.service.TranscriptService;
import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.web.dto.request.PageAndSizeData;
import com.project.medicalanalize.web.dto.request.SortData;
import com.project.medicalanalize.web.dto.request.TranscriptRequestDto;
import com.project.medicalanalize.web.dto.response.PageData;
import com.project.medicalanalize.web.dto.response.TranscriptResponseDto;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranscriptFacadeImpl implements TranscriptFacade {

    private final TranscriptService transcriptService;

    public TranscriptFacadeImpl(TranscriptService transcriptService) {
        this.transcriptService = transcriptService;
    }

    @Override
    public void create(TranscriptRequestDto transcriptRequestDto) {
        Transcript transcript = new Transcript();
        transcript.setPrice(transcriptRequestDto.getPrice());
        transcript.setBadHabits(transcriptRequestDto.getBadHabits());
        transcript.setDrugsTaken(transcriptRequestDto.getDrugsTaken());
        transcript.setChronicDiseases(transcriptRequestDto.getChronicDiseases());
        transcript.setBurglaryComplaints(transcriptRequestDto.getBurglaryComplaints());
        transcript.setHereditary_diseases(transcriptRequestDto.getHereditary_diseases());
        transcript.setFeaturesNutrition(transcriptRequestDto.getFeaturesNutrition());
        transcript.setFile(transcriptRequestDto.getFile());
//        setterTranscript(transcriptRequestDto, transcript);
        transcriptService.create(transcript);
    }

    @Override
    public void update(TranscriptRequestDto transcriptRequestDto, long id) {
        Transcript transcript = transcriptService.findById(id).get();
        transcript.setId(id);
        transcript.setPrice(transcriptRequestDto.getPrice());
        transcript.setBadHabits(transcriptRequestDto.getBadHabits());
        transcript.setDrugsTaken(transcriptRequestDto.getDrugsTaken());
        transcript.setChronicDiseases(transcriptRequestDto.getChronicDiseases());
        transcript.setBurglaryComplaints(transcriptRequestDto.getBurglaryComplaints());
        transcript.setHereditary_diseases(transcriptRequestDto.getHereditary_diseases());
        transcript.setFeaturesNutrition(transcriptRequestDto.getFeaturesNutrition());
        transcript.setFile(transcriptRequestDto.getFile());
//        setterTranscript(transcriptRequestDto, transcript);
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

        DataTableResponse<Transcript> all = transcriptService.findAll(dataTableRequest);

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

    private Transcript setterTranscript(TranscriptRequestDto transcriptRequestDto, Transcript transcript) {
        transcript.setPrice(transcriptRequestDto.getPrice());
        transcript.setBadHabits(transcriptRequestDto.getBadHabits());
        transcript.setDrugsTaken(transcriptRequestDto.getDrugsTaken());
        transcript.setChronicDiseases(transcriptRequestDto.getChronicDiseases());
        transcript.setBurglaryComplaints(transcriptRequestDto.getBurglaryComplaints());
        transcript.setHereditary_diseases(transcriptRequestDto.getHereditary_diseases());
        transcript.setFeaturesNutrition(transcriptRequestDto.getFeaturesNutrition());
        transcript.setFile(transcriptRequestDto.getFile());
        return transcript;
    }
}