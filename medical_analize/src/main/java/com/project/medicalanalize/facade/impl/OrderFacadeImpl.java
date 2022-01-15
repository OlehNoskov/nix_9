//package com.project.medicalanalize.facade.impl;
//
//import com.project.medicalanalize.facade.OrderFacade;
//import com.project.medicalanalize.persistence.datatable.DataTableRequest;
//import com.project.medicalanalize.persistence.datatable.DataTableResponse;
//import com.project.medicalanalize.persistence.entity.order.CheckUp;
//import com.project.medicalanalize.persistence.entity.order.ComprehensiveConsultationOrder;
//import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
//import com.project.medicalanalize.service.CheckUpService;
//import com.project.medicalanalize.service.ComprehensiveConsultationOrderService;
//import com.project.medicalanalize.service.TranscriptService;
//import com.project.medicalanalize.util.WebRequestUtil;
//import com.project.medicalanalize.web.dto.request.PageAndSizeData;
//import com.project.medicalanalize.web.dto.request.RequestDto;
//import com.project.medicalanalize.web.dto.request.SortData;
//import com.project.medicalanalize.web.dto.response.*;
//
//import org.springframework.web.context.request.WebRequest;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class OrderFacadeImpl implements OrderFacade {
//
//    private final TranscriptService transcriptService;
//    private final CheckUpService checkUpService;
//    private final ComprehensiveConsultationOrderService consultationOrderService;
//
//    public OrderFacadeImpl(TranscriptService transcriptService, CheckUpService checkUpService,
//                           ComprehensiveConsultationOrderService consultationOrderService) {
//        this.transcriptService = transcriptService;
//        this.checkUpService = checkUpService;
//        this.consultationOrderService = consultationOrderService;
//    }
//
//    @Override
//    public void create(RequestDto requestDto) {
//
//    }
//
//    @Override
//    public void update(RequestDto requestDto, long id) throws ParseException {
//
//    }
//
//    @Override
//    public void delete(long id) {
//
//    }
//
//    @Override
//    public ResponseDto findById(long id) {
//        return null;
//    }
//
//    @Override
//    public PageData findAll(WebRequest request) {
//        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
//        SortData sortData = WebRequestUtil.generateSortData(request);
//
//        DataTableRequest dataTableRequest = new DataTableRequest();
//        dataTableRequest.setSize(pageAndSizeData.getSize());
//        dataTableRequest.setPage(pageAndSizeData.getPage());
//        dataTableRequest.setSort(sortData.getSort());
//        dataTableRequest.setOrder(sortData.getOrder());
//
//        DataTableResponse<TranscriptOrder> all1 = transcriptService.findAll(dataTableRequest);
//        DataTableResponse<CheckUp> all2 = checkUpService.findAll(dataTableRequest);
//        DataTableResponse<ComprehensiveConsultationOrder> all3 = consultationOrderService.findAll(dataTableRequest);
//
//        List<TranscriptResponseDto> list1 = all1.getItems().
//                stream().
//                map(TranscriptResponseDto::new).
//                collect(Collectors.toList());
//
//        List<CheckUpResponseDto> list2 = all2.getItems().
//                stream().
//                map(CheckUpResponseDto::new).
//                collect(Collectors.toList());
//
//        List<ConsultationResponseDto> list3 = all3.getItems().
//                stream().
//                map(ConsultationResponseDto::new).
//                collect(Collectors.toList());
//
//        List<TranscriptResponseDto> newList = new ArrayList<TranscriptResponseDto>();
//        newList.addAll(list1);
//        newList.addAll((Collection<? extends TranscriptResponseDto>) list2);
//
//        List<OrderResponseDto> listResult = all1.getItems().
//                stream().
//                map(ConsultationResponseDto::new).
//                collect(Collectors.toList());
//
//        PageData<TranscriptResponseDto> pageData = new PageData<>();
//        pageData.setItems(list);
//        pageData.setCurrentPage(pageAndSizeData.getPage());
//        pageData.setPageSize(pageAndSizeData.getSize());
//        pageData.setOrder(sortData.getOrder());
//        pageData.setSort(sortData.getSort());
//        pageData.setItemsSize(all.getItemsSize());
//        pageData.initPaginationState(pageData.getCurrentPage());
//        return pageData;
//    }
//}
