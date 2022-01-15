package com.project.medicalanalize.persistence.datatable;

import lombok.Data;

import java.util.Collections;
import java.util.Map;

@Data
public class DataTableRequest {
    private int page;
    private int size;
    private String order;
    private String sort;
    private Map<String, String[]> requestParamMap;

    public DataTableRequest() {
        requestParamMap = Collections.emptyMap();
    }
}