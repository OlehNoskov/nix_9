package com.project.medicalanalize.web.controller.admin;

import com.project.medicalanalize.facade.CheckUpFacade;
import com.project.medicalanalize.web.controller.AbstractController;
import com.project.medicalanalize.web.dto.response.CheckUpResponseDto;
import com.project.medicalanalize.web.dto.response.PageData;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.project.medicalanalize.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/admin/check_up")
public class AdminCheckUpController extends AbstractController {

    private final CheckUpFacade checkUpFacade;

    public AdminCheckUpController(CheckUpFacade checkUpFacade) {
        this.checkUpFacade = checkUpFacade;
    }

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("created", "created", "created"),
                new HeaderName("name patient",null ,null ),
                new HeaderName("details", null, null),
                new HeaderName("edit", null, null),
                new HeaderName("delete", null, null)
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnTitles = getColumnTitles();
        PageData response = checkUpFacade.findAllCheckUpAdmin(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List headerDataList = getHeaderDataList(columnTitles, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/admin/check_up/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Check_Up");
        return "pages/admin/admin_check_up_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/admin/dashboard", model);
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
}