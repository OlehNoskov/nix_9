package com.project.medicalanalize.web.controller.admin;

import com.project.medicalanalize.facade.TranscriptFacade;
import com.project.medicalanalize.persistence.entity.order.TranscriptOrder;
import com.project.medicalanalize.web.controller.AbstractController;
import com.project.medicalanalize.web.dto.response.PageData;
import com.project.medicalanalize.web.dto.response.TranscriptResponseDto;

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
@RequestMapping("/admin/transcript")
public class AdminTranscriptController extends AbstractController {

    private final TranscriptFacade transcriptFacade;

    public AdminTranscriptController(TranscriptFacade transcriptFacade) {
        this.transcriptFacade = transcriptFacade;
    }

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("created", "created", "created"),
                new HeaderName("name patient", null, null),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
    }

    @GetMapping
    public String findAllSuccess(Model model, WebRequest webRequest) {
        HeaderName[] columnTitles = getColumnTitles();
        PageData response = transcriptFacade.findAllSuccessTranscriptVisibleAdmin(webRequest);
        List headerDataList = getHeaderDataList(columnTitles, response);
        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/admin/transcript/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All orders");
        return "pages/admin/admin_transcript_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/admin/transcript", model);
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnTitles, PageData<TranscriptResponseDto> response) {
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
    public String detailsTranscript(@PathVariable Long id, Model model) {
        TranscriptResponseDto transcriptResponseDto = transcriptFacade.findById(id);
        model.addAttribute("transcript", transcriptResponseDto);
        return "pages/admin/orders_success_details/transcript_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        transcriptFacade.delete(id);
        return "redirect:/admin/transcript";
    }
}