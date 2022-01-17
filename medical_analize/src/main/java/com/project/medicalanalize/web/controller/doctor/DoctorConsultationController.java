package com.project.medicalanalize.web.controller.doctor;

import com.project.medicalanalize.facade.ConsultationOrderFacade;
import com.project.medicalanalize.web.controller.AbstractController;
import com.project.medicalanalize.web.dto.request.ConsultationRequestDto;
import com.project.medicalanalize.web.dto.response.ConsultationResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

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
@RequestMapping("/doctors/consultation")
public class DoctorConsultationController extends AbstractController {

    private final ConsultationOrderFacade consultationOrderFacade;

    public DoctorConsultationController(ConsultationOrderFacade consultationOrderFacade) {
        this.consultationOrderFacade = consultationOrderFacade;
    }

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("Created", "created", "created"),
                new HeaderName("Name patient", "firstname", "firstname"),
                new HeaderName("Price", "price", "price"),
                new HeaderName("Review", null, null),
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnTitles = getColumnTitles();
        PageData response = consultationOrderFacade.findAllConsultationOrdersReviewDoctors(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnTitles, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/doctors/consultation/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All consultation");
        return "pages/doctor/doctor_all_consultation";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/doctors/dashboard", model);
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnTitles, PageData<ConsultationResponseDto> response) {
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

    @GetMapping("/details/{id}")
    public String detailsConsultation(@PathVariable Long id, Model model) {
        ConsultationResponseDto checkUpResponseDto = consultationOrderFacade.findById(id);
        model.addAttribute("consultation", checkUpResponseDto);
        return "pages/doctor/consultation_details";
    }

    @PostMapping("/details/{id}")
    public String answerPatientConsultation(@PathVariable Long id, @ModelAttribute("consultation") ConsultationRequestDto consultationRequestDto) throws ParseException {
        consultationOrderFacade.update(consultationRequestDto, id);
        return "redirect:/doctors/dashboard";
    }
}