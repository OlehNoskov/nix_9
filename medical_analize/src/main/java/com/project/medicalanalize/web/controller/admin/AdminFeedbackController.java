package com.project.medicalanalize.web.controller.admin;

import com.project.medicalanalize.facade.FeedbackFacade;
import com.project.medicalanalize.web.controller.AbstractController;
import com.project.medicalanalize.web.dto.request.DoctorRequestDto;
import com.project.medicalanalize.web.dto.request.FeedbackRequestDto;
import com.project.medicalanalize.web.dto.response.DoctorResponseDto;
import com.project.medicalanalize.web.dto.response.FeedbackResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.project.medicalanalize.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/admin/feedback")
public class AdminFeedbackController extends AbstractController {

    private final FeedbackFacade feedbackFacade;

    public AdminFeedbackController(FeedbackFacade feedbackFacade) {
        this.feedbackFacade = feedbackFacade;
    }

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("created", "created", "created"),
                new HeaderName("name patient",null ,null ),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        AbstractController.HeaderName[] columnTitles = getColumnTitles();
        PageData response = feedbackFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List headerDataList = getHeaderDataList(columnTitles, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/admin/feedback/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All feedback");
        return "pages/admin/admin_feedback_all";
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnTitles, PageData<FeedbackResponseDto> response) {
        List<HeaderData> headerDataList = new ArrayList<>();
        for (HeaderName headerName : columnTitles) {
            HeaderData data = new HeaderData();
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

//    @GetMapping("/details/{id}")
//    public String detailsFeedback(@PathVariable Long id, Model model) {
//        FeedbackResponseDto feedbackResponseDto = feedbackFacade.findById(id);
//        model.addAttribute("feedback", feedbackResponseDto);
//        return "pages/admin/update/doctor_edit";
//    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        feedbackFacade.delete(id);
        return "redirect:/admin/feedback";
    }
}
