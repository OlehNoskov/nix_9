package com.project.medicalanalize.facade;

import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.util.WebRequestUtil;
import com.project.medicalanalize.web.dto.request.PageAndSizeData;
import com.project.medicalanalize.web.dto.request.SortData;

import org.springframework.web.context.request.WebRequest;

public class FindAllOrdersUtil {

    public static DataTableRequest findAllOrders(WebRequest request){

        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());

        return dataTableRequest;//TODO
    }
}
