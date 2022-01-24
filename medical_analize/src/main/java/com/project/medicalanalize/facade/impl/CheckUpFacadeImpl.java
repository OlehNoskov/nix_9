package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.CheckUpFacade;
import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.user.Doctor;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.service.CheckUpService;
import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.web.dto.request.CheckUpRequestDto;
import com.project.medicalanalize.web.dto.request.PageAndSizeData;
import com.project.medicalanalize.web.dto.request.SortData;
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
        checkUp.setId(id);
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
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<CheckUp> all = checkUpService.findAll(dataTableRequest);

        List<CheckUpResponseDto> list = all.getItems().
                stream().filter(c -> c.getPatient().getId().equals(userFacade.getCurrentUser().getId())).
                filter(checkUp -> checkUp.getVisible().equals(false)).
                map(CheckUpResponseDto::new).
                collect(Collectors.toList());

        return getPageData(pageAndSizeData, sortData, all, list);
    }

    private CheckUp setterFieldCheckUp(CheckUpRequestDto checkUpRequestDto, CheckUp checkUp) {
        checkUp.setBadHabits(checkUpRequestDto.getBadHabits());
        checkUp.setDrugsTaken(checkUpRequestDto.getDrugsTaken());
        checkUp.setChronicDiseases(checkUpRequestDto.getChronicDiseases());
        checkUp.setBurglaryComplaints(checkUpRequestDto.getBurglaryComplaints());
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
        checkUp.setPrice(checkUp.getPrice());
        checkUp.setPatient((Patient) userFacade.getCurrentUser());
        return checkUpService.createAndFind(checkUp);
    }

    @Override
    public PageData findAllCheckUpOrdersReviewDoctors(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());
        DataTableResponse<CheckUp> all = checkUpService.findAllTranscriptVisibleDoctor(dataTableRequest);
        return getPageData(pageAndSizeData, sortData, all);
    }

    @Override
    public PageData findAllCheckUpSuccessAdmin(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<CheckUp> all = checkUpService.findAllSuccessTranscriptVisibleAdmin(dataTableRequest);

        return getPageData(pageAndSizeData, sortData, all);
    }

    private PageData getPageData(PageAndSizeData pageAndSizeData, SortData sortData, DataTableResponse<CheckUp> all) {
        List<CheckUpResponseDto> list = all.getItems().
                stream().
                map(CheckUpResponseDto::new).
                collect(Collectors.toList());
        return getPageData(pageAndSizeData, sortData, all, list);
    }

    private PageData getPageData(PageAndSizeData pageAndSizeData, SortData sortData, DataTableResponse<CheckUp> all, List<CheckUpResponseDto> list) {
        PageData<CheckUpResponseDto> pageData = new PageData<>();
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