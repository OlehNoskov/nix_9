package com.project.medicalanalize.web.controller.doctor;

import com.project.medicalanalize.facade.CheckUpFacade;
import com.project.medicalanalize.web.controller.AbstractController;
import com.project.medicalanalize.web.dto.request.CheckUpRequestDto;
import com.project.medicalanalize.web.dto.response.CheckUpResponseDto;
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
@RequestMapping("/doctors/check_up")
public class DoctorCheckUpController extends AbstractController {

    private final CheckUpFacade checkUpFacade;

    public DoctorCheckUpController(CheckUpFacade checkUpFacade) {
        this.checkUpFacade = checkUpFacade;
    }

    private AbstractController.HeaderName[] getColumnTitles() {
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
        PageData response = checkUpFacade.findAllCheckUpOrdersReviewDoctors(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List headerDataList = getHeaderDataList(columnTitles, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/doctors/check_up/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All check_Up");
        return "pages/doctor/doctor_all_check_up";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/doctors/check_up", model);
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnTitles, PageData<CheckUpResponseDto> response) {
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
    public String detailsCheckUp(@PathVariable Long id, Model model) {
        CheckUpResponseDto checkUpResponseDto = checkUpFacade.findById(id);
        model.addAttribute("check_up", checkUpResponseDto);
        return "pages/doctor/check_up_details";
    }

    @PostMapping("/details/{id}")
    public String answerPatientCheckUp(@PathVariable Long id, @ModelAttribute("check_up") CheckUpRequestDto checkUpRequestDto) throws ParseException {
        checkUpFacade.update(checkUpRequestDto, id);
        return "redirect:/doctors/dashboard";
    }
}