package com.project.medicalanalize.web.controller.patient.successorders;

import com.project.medicalanalize.facade.ConsultationOrderFacade;
import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.entity.user.Patient;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.web.controller.AbstractController;
import com.project.medicalanalize.web.dto.response.ConsultationResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.project.medicalanalize.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/patient/completed/order/consultation")
public class ConsultationOrderController extends AbstractController {

    private final ConsultationOrderFacade consultationOrderFacade;
    private final UserFacade userFacade;

    public ConsultationOrderController(ConsultationOrderFacade consultationOrderFacade, UserFacade userFacade) {
        this.consultationOrderFacade = consultationOrderFacade;
        this.userFacade = userFacade;
    }

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("Order completion date", "updated", "updated"),
                new HeaderName("Doctor", null, null),
                new HeaderName("Details", null, null),
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        User user = userFacade.getCurrentUser();
        HeaderName[] columnTitles = getColumnTitles();
        PageData<ConsultationResponseDto> response = consultationOrderFacade.findAllSuccessConsultationPatient(webRequest, user.getId());
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnTitles, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/patient/completed/order/consultation/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All my completed consultations");
        return "pages/patient/order/success_orders/success_order_consultation_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/patient/completed/order/consultation", model);
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnTitles, PageData<ConsultationResponseDto> response) {
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
    public String detailsConsultation(@PathVariable Long id, Model model) {
        ConsultationResponseDto consultationResponseDto = consultationOrderFacade.findById(id);
        Patient patient = consultationResponseDto.getPatient();
        model.addAttribute("consultation", consultationResponseDto);
        model.addAttribute("patient", patient);
        return "pages/patient/order/success_orders/consultation_details";
    }
}