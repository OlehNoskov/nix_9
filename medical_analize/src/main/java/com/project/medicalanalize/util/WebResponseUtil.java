package com.project.medicalanalize.util;

import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.BaseEntity;
import com.project.medicalanalize.web.dto.response.PageData;
import com.project.medicalanalize.web.dto.response.ResponseDto;

public class WebResponseUtil {

    private WebResponseUtil() {}

    public static PageData<? extends ResponseDto> initPageData(DataTableResponse<? extends BaseEntity> tableResponse) {
        PageData<? extends ResponseDto> pageData = new PageData<>();
        pageData.setCurrentPage(tableResponse.getCurrentPage());
        pageData.setPageSize(tableResponse.getPageSize());
        pageData.setOrder(tableResponse.getOrder());
        pageData.setSort(tableResponse.getSort());
        pageData.setItemsSize(tableResponse.getItemsSize());
        pageData.initPaginationState(tableResponse.getCurrentPage());
        return pageData;
    }
}