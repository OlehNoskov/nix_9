package com.project.medicalanalize.web.controller.patient.successorders;

import com.project.medicalanalize.facade.CheckUpFacade;
import com.project.medicalanalize.web.controller.AbstractController;
import com.project.medicalanalize.web.dto.response.CheckUpResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

import static com.project.medicalanalize.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/patient/completed/order/check_up")
public class CheckUpOrderController extends AbstractController {

    private final CheckUpFacade checkUpFacade;

    public CheckUpOrderController(CheckUpFacade checkUpFacade) {
        this.checkUpFacade = checkUpFacade;
    }

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("Order completion date", "updated", "updated"),
                new HeaderName("Details", null, null),
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnTitles = getColumnTitles();
        PageData<CheckUpResponseDto> response = checkUpFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnTitles, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/patient/completed/order/check_up/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All my completed check-up");
        return "pages/patient/order/success_orders/success_order_check_up_all";
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
        return "pages/patient/order/success_orders/check_up_details";
    }
}
