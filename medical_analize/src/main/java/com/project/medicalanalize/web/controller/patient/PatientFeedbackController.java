package com.project.medicalanalize.web.controller.patient;

import com.project.medicalanalize.facade.FeedbackFacade;
import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.web.controller.AbstractController;
import com.project.medicalanalize.web.dto.request.FeedbackRequestDto;
import com.project.medicalanalize.web.dto.response.*;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.project.medicalanalize.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/patient/feedback")
public class PatientFeedbackController extends AbstractController {

    private final FeedbackFacade feedbackFacade;
    private final UserFacade userFacade;

    public PatientFeedbackController(FeedbackFacade feedbackFacade, UserFacade userFacade) {
        this.feedbackFacade = feedbackFacade;
        this.userFacade = userFacade;
    }

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("Created", "created", "created"),
                new HeaderName("Edit", null, null),
                new HeaderName("Delete", null, null),
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        User user = userFacade.getCurrentUser();
        HeaderName[] columnTitles = getColumnTitles();
        PageData response = feedbackFacade.findAllFeedbackPatient(webRequest, user.getId());
        response.initPaginationState(response.getCurrentPage());
        List headerDataList = getHeaderDataList(columnTitles, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/patient/feedback/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All my feedbacks");
        return "pages/patient/feedback/feedback_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/patient/feedback", model);
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

    @GetMapping("/new")
    public String createNewFeedback(Model model) {
        model.addAttribute("feedback", new FeedbackRequestDto());
        return "pages/patient/feedback/feedback_new";
    }

    @PostMapping("/new")
    public String createNewFeedback(@ModelAttribute("feedback") FeedbackRequestDto feedbackRequestDto) {
        feedbackFacade.create(feedbackRequestDto);
        return "redirect:/patients/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String detailsFeedback(@PathVariable Long id, Model model) {
        FeedbackResponseDto feedbackResponseDto = feedbackFacade.findById(id);
        model.addAttribute("feedback", feedbackResponseDto);
        return "pages/patient/feedback/feedback_details";
    }

    @PostMapping("/edit/{id}")
    public String updateFeedback(@PathVariable Long id, @ModelAttribute("feedback") FeedbackRequestDto feedbackRequestDto) throws ParseException {
        feedbackFacade.update(feedbackRequestDto, id);
        return "redirect:/patient/feedback";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        feedbackFacade.delete(id);
        return "redirect:/patient/feedback";
    }
}