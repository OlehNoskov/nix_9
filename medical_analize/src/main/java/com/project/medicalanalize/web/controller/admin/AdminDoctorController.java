package com.project.medicalanalize.web.controller.admin;

import com.project.medicalanalize.facade.DoctorFacade;
import com.project.medicalanalize.web.controller.AbstractController;
import com.project.medicalanalize.web.dto.request.DoctorRequestDto;
import com.project.medicalanalize.web.dto.response.DoctorResponseDto;
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
@RequestMapping("/admin/doctors")
public class AdminDoctorController extends AbstractController {

    private final DoctorFacade doctorFacade;

    public AdminDoctorController(DoctorFacade doctorFacade) {
        this.doctorFacade = doctorFacade;
    }

    private HeaderName[] getColumnTitles() {
        return new HeaderName[]{
                new HeaderName("#", null, null),
                new HeaderName("firstname", "firstname", "firstname"),
                new HeaderName("lastname", "lastname", "lastname"),
                new HeaderName("details", null, null),
                new HeaderName("edit", null, null),
                new HeaderName("delete", null, null)
        };
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnTitles = getColumnTitles();
        PageData<DoctorResponseDto> response = doctorFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = getHeaderDataList(columnTitles, response);

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/admin/doctors/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Doctors");
        return "pages/admin/admin_doctors_all";

    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/admin/doctors", model);
    }

    private List<HeaderData> getHeaderDataList(HeaderName[] columnTitles, PageData<DoctorResponseDto> response) {
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
    public String details(@PathVariable Long id, Model model) {
        DoctorResponseDto doctorResponseDto = doctorFacade.findById(id);
        model.addAttribute("doctor", doctorResponseDto);
        return "pages/admin/update/doctor_details";
    }

    @GetMapping("/edit/{id}")
    public String profileEdit(@PathVariable Long id, Model model) {
        DoctorResponseDto doctorResponseDto = doctorFacade.findById(id);
        model.addAttribute("doctor", doctorResponseDto);
        return "pages/admin/update/doctor_edit";
    }

    @PostMapping("/edit/{id}")
    public String updatePatient(@PathVariable Long id, @ModelAttribute("doctor") DoctorRequestDto doctorRequestDto) throws ParseException {
        doctorFacade.update(doctorRequestDto, id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        doctorFacade.delete(id);
        return "redirect:/admin/doctors";
    }
}