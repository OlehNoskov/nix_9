package com.project.medicalanalize.facade.impl.typetranscript;

import com.project.medicalanalize.facade.typetranscript.GeneralBloodFacade;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.transcript.GeneralBlood;
import com.project.medicalanalize.service.typetranscript.GeneralBloodService;
import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.web.dto.request.PageAndSizeData;
import com.project.medicalanalize.web.dto.request.SortData;
import com.project.medicalanalize.web.dto.request.typetranscript.GeneralBloodRequestDto;
import com.project.medicalanalize.web.dto.response.PageData;
import com.project.medicalanalize.web.dto.response.typetranscript.GeneralBloodResponseDto;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GeneralBloodFacadeImpl implements GeneralBloodFacade {

    private final GeneralBloodService generalBloodService;

    public GeneralBloodFacadeImpl(GeneralBloodService generalBloodService) {
        this.generalBloodService = generalBloodService;
    }

    @Override
    public void create(GeneralBloodRequestDto generalBloodRequestDto) {
        GeneralBlood generalBlood = new GeneralBlood();
        setterFields(generalBloodRequestDto, generalBlood);
        generalBlood.setTranscript(generalBloodRequestDto.getTranscriptOrder());
        generalBloodService.create(generalBlood);
    }

    @Override
    public void update(GeneralBloodRequestDto generalBloodRequestDto, long id) throws ParseException {
        GeneralBlood generalBlood = generalBloodService.findById(id).get();
        generalBlood.setId(id);
        generalBlood.setUpdated(new Timestamp(System.currentTimeMillis()));
        setterFields(generalBloodRequestDto, generalBlood);
        generalBloodService.update(generalBlood);
    }

    @Override
    public void delete(long id) {
        generalBloodService.delete(id);
    }

    @Override
    public GeneralBloodResponseDto findById(long id) {
        return new GeneralBloodResponseDto(generalBloodService.findById(id).get());
    }

    @Override
    public PageData<GeneralBloodResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<GeneralBlood> all = generalBloodService.findAll(dataTableRequest);

        List<GeneralBloodResponseDto> list = all.getItems().
                stream().
                map(GeneralBloodResponseDto::new).
                collect(Collectors.toList());

        PageData<GeneralBloodResponseDto> pageData = new PageData<>();
        pageData.setItems(list);
        pageData.setCurrentPage(pageAndSizeData.getPage());
        pageData.setPageSize(pageAndSizeData.getSize());
        pageData.setOrder(sortData.getOrder());
        pageData.setSort(sortData.getSort());
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private GeneralBlood setterFields(GeneralBloodRequestDto generalBloodRequestDto, GeneralBlood generalBlood) {
        generalBlood.setHemoglobin(generalBloodRequestDto.getHemoglobin());
        generalBlood.setErythrocytes(generalBloodRequestDto.getErythrocytes());
        generalBlood.setReticulocytes(generalBloodRequestDto.getReticulocytes());
        generalBlood.setPlatelets(generalBloodRequestDto.getPlatelets());
        generalBlood.setLeukocytes(generalBloodRequestDto.getLeukocytes());
        generalBlood.setSoe(generalBloodRequestDto.getSoe());
        generalBlood.setMyelocytes(generalBloodRequestDto.getMyelocytes());
        generalBlood.setMetamyelocytes(generalBloodRequestDto.getMetamyelocytes());
        generalBlood.setStab(generalBloodRequestDto.getStab());
        generalBlood.setSegmented_nuclear(generalBloodRequestDto.getSegmented_nuclear());
        generalBlood.setEosinophils(generalBloodRequestDto.getEosinophils());
        generalBlood.setBasophils(generalBloodRequestDto.getBasophils());
        generalBlood.setLymphocytes(generalBloodRequestDto.getLymphocytes());
        generalBlood.setMonocytes(generalBloodRequestDto.getMonocytes());
        return generalBlood;
    }
}