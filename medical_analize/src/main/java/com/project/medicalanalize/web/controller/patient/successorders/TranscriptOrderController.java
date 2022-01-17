package com.project.medicalanalize.web.controller.patient.successorders;

import com.project.medicalanalize.facade.TranscriptFacade;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.web.controller.AbstractController;
import com.project.medicalanalize.web.dto.response.PageData;
import com.project.medicalanalize.web.dto.response.TranscriptResponseDto;

import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

import static com.project.medicalanalize.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/patient/completed/order/transcript")
public class TranscriptOrderController extends AbstractController {

    private final TranscriptFacade transcriptFacade;

    public TranscriptOrderController(TranscriptFacade transcriptFacade) {
        this.transcriptFacade = transcriptFacade;
    }

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("Order completion date", "updated", "updated"),
                new AbstractController.HeaderName("Details", null, null),
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnTitles = getColumnTitles();
        PageData<TranscriptResponseDto> response = transcriptFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List<AbstractController.HeaderData> headerDataList = getHeaderDataList(columnTitles, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/patient/completed/order/transcript/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All my completed transcript");
        return "pages/patient/order/success_orders/success_order_transcript_all";
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnTitles, PageData<TranscriptResponseDto> response) {
        List<HeaderData> headerDataList = new ArrayList<>();
        for (HeaderName headerName : columnTitles) {
            HeaderData data = new AbstractController.HeaderData();
            data.setHeaderName(headerName.getColumnName());
            if (StringUtils.isBlank(headerName.getTableName())) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(headerName.getDbName());
                if (response.getSort().equals(headerName.getDbName())) {
                    data.setActive(true);
                    data.setOrder(response.getOrder());
                } else {
                    data.setActive(false);
                    data.setOrder(DEFAULT_ORDER_PARAM_VALUE);
                }
            }
            headerDataList.add(data);
        }
        return headerDataList;
    }

    @GetMapping("/details/{id}")
    public String detailsTranscript(@PathVariable Long id, Model model) {
        TranscriptResponseDto transcriptResponseDto = transcriptFacade.findById(id);
        Patient patient = transcriptResponseDto.getPatient();
        model.addAttribute("transcript", transcriptResponseDto);
        model.addAttribute("patient", patient);
        return "pages/patient/order/success_orders/transcript_details";
    }
}