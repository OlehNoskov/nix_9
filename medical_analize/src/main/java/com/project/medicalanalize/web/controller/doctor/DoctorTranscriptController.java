package com.project.medicalanalize.web.controller.doctor;

import com.project.medicalanalize.facade.TranscriptFacade;
import com.project.medicalanalize.web.controller.AbstractController;
import com.project.medicalanalize.web.dto.request.DoctorRequestDto;
import com.project.medicalanalize.web.dto.request.TranscriptRequestDto;
import com.project.medicalanalize.web.dto.response.PageData;
import com.project.medicalanalize.web.dto.response.TranscriptResponseDto;

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
@RequestMapping("/doctors/transcript")
public class DoctorTranscriptController extends AbstractController {

    private final TranscriptFacade transcriptFacade;

    public DoctorTranscriptController(TranscriptFacade transcriptFacade) {
        this.transcriptFacade = transcriptFacade;
    }

    private AbstractController.HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("created", "created", "created"),
                new HeaderName("name patient", "firstname", "firstname"),
                new HeaderName("price", "price", "price"),
                new HeaderName("add", null, null),
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnTitles = getColumnTitles();
        PageData<TranscriptResponseDto> response = transcriptFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnTitles, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/doctors/transcript/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All transcript");
        return "pages/doctor/doctor_all_transcript";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/doctors/dashboard", model);
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
        return "pages/doctor/transcript_details";
    }

    @PostMapping("/details/{id}")
    public String answerPatient(@PathVariable Long id, @ModelAttribute("transcript") TranscriptRequestDto transcriptRequestDto) throws ParseException {
        transcriptFacade.update(transcriptRequestDto, id);
        return "redirect:/doctors/dashboard";
    }
}