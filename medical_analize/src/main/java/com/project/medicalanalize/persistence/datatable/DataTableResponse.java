package com.project.medicalanalize.persistence.datatable;

import com.project.medicalanalize.persistence.entity.BaseEntity;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class DataTableResponse <E extends BaseEntity>{
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long itemsSize;
    private String sort;
    private String order;
    private List<E> items;
    private Map<Object, Object> otherParamMap;

    public DataTableResponse() {
        this.pageSize = 10;
        this.items = Collections.emptyList();
        this.otherParamMap = Collections.emptyMap();
    }
}