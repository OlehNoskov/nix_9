package com.project.medicalanalize.web.controller;

import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.web.dto.response.PageData;
import com.project.medicalanalize.web.dto.response.ResponseDto;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractController {

    protected void showInfo(Model model, String message) {
        model.addAttribute("message", message);
        showMessage(model, true);
    }

    protected void showInfo(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("message", message);
    }

    protected void showError(Model model, String message) {
        model.addAttribute("errorMessage", message);
        showMessage(model, true);
    }

    protected void showError(RedirectAttributes redirectAttributes, String error) {
        redirectAttributes.addFlashAttribute("errorMessage", error);
    }

    protected void showWarn(Model model, String message) {
        model.addAttribute("warnMessage", message);
        showMessage(model, true);
    }

    protected void showWarn(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("warnMessage", message);
    }

    protected void showMessage(Model model, boolean show) {
        model.addAttribute("showMessage", show);
    }

    protected static class HeaderName {

        @Getter
        @Setter
        private String columnName;

        @Getter
        @Setter
        private String tableName;

        @Getter
        @Setter
        private String dbName;

        public HeaderName(String columnName, String tableName, String dbName) {
            this.columnName = columnName;
            this.tableName = tableName;
            this.dbName = dbName;
        }
    }

    public static class HeaderData {

        @Getter
        @Setter
        private String headerName;

        @Getter
        @Setter
        private boolean active;

        @Getter
        @Setter
        private boolean sortable;

        @Getter
        @Setter
        private String sort;

        @Getter
        @Setter
        private String order;
    }

    protected void initDataTable(
            PageData<? extends ResponseDto> response,
            HeaderName[] columnNames,
            Model model) {
        List<HeaderData> headerDataList = new ArrayList<>();
        for (HeaderName headerName : columnNames) {
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
                    data.setOrder(WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE);
                }
            }
            headerDataList.add(data);
        }
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("pageData", response);
    }

    public ModelAndView findAllRedirect(WebRequest request, ModelMap model, String redirectUrl) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/" + redirectUrl, model);
    }
}