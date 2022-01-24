package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.FeedbackFacade;
import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.feedback.Feedback;
import com.project.medicalanalize.persistence.entity.order.CheckUp;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.service.FeedbackService;
import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.util.WebResponseUtil;
import com.project.medicalanalize.web.dto.request.FeedbackRequestDto;
import com.project.medicalanalize.web.dto.request.PageAndSizeData;
import com.project.medicalanalize.web.dto.request.SortData;
import com.project.medicalanalize.web.dto.response.CheckUpResponseDto;
import com.project.medicalanalize.web.dto.response.FeedbackResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackFacadeImpl implements FeedbackFacade {

    private final FeedbackService feedbackService;
    private final UserFacade userFacade;

    public FeedbackFacadeImpl(FeedbackService feedbackService, UserFacade userFacade) {
        this.feedbackService = feedbackService;
        this.userFacade = userFacade;
    }

    @Override
    public void create(FeedbackRequestDto feedbackRequestDto) {
        Feedback feedback = new Feedback();
        feedback.setPatient((Patient) userFacade.getCurrentUser());
        feedback.setTextFeedback(feedbackRequestDto.getTextFeedback());
        feedbackService.create(feedback);
    }

    @Override
    public void update(FeedbackRequestDto feedbackRequestDto, long id) {
        Feedback feedback = feedbackService.findById(id).get();
        feedback.setId(id);
        feedback.setUpdated(new Timestamp(System.currentTimeMillis()));
        feedback.setTextFeedback(feedbackRequestDto.getTextFeedback());
        feedbackService.update(feedback);
    }

    @Override
    public void delete(long id) {
        feedbackService.delete(id);
    }

    @Override
    public FeedbackResponseDto findById(long id) {
        return new FeedbackResponseDto(feedbackService.findById(id).get());
    }

    @Override
    public PageData<FeedbackResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        DataTableResponse<Feedback> all = feedbackService.findAll(dataTableRequest);

        List<FeedbackResponseDto> list = all.getItems().
                stream().
                map(FeedbackResponseDto::new).
                collect(Collectors.toList());

        PageData<FeedbackResponseDto> pageData = new PageData<>();
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
    public PageData<FeedbackResponseDto> findAllFeedbackPatient(WebRequest request, Long idPatient) {
        User user = userFacade.getCurrentUser();
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Feedback> tableResponse = feedbackService.findAllFeedbacksPatient(dataTableRequest, user.getId());
        List<FeedbackResponseDto> list = tableResponse.getItems().stream().
                map(FeedbackResponseDto::new).
                collect(Collectors.toList());
        PageData<FeedbackResponseDto> pageData = (PageData<FeedbackResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(list);
        return pageData;
    }
}