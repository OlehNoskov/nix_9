package com.project.medicalanalize.web.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageData<REQ extends ResponseDto> {

    private int currentPage;
    private int pageSize;
    private int totalPageSize;
    private long itemsSize;
    private List<REQ> items;
    private final int[] pageSizeItems;
    private boolean showFirst;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showLast;
    private String sort;
    private String order;
    private int currentShowFromEntries;
    private int currentShowToEntries;

    public PageData() {
        this.currentPage = 0;
        this.pageSizeItems = new int[]{10, 25, 50, 100};
        this.pageSize = this.pageSizeItems[0];
        this.totalPageSize = 0;
        this.itemsSize = 0;
        this.items = new ArrayList<>();
        this.showFirst = false;
        this.showPrevious = false;
        this.showNext = false;
        this.showLast = false;
    }

    public void initPaginationState(int currentPage) {
        if (pageSize < itemsSize) {
            this.totalPageSize = (int) itemsSize / pageSize;
            this.showFirst = this.currentPage != 1;
            this.showPrevious = this.currentPage - 1 != 0;
            this.showLast = this.currentPage - 1 != totalPageSize;
            this.showNext = this.currentPage - 1 != totalPageSize;
        }
        currentShowFromEntries = ((this.currentPage - 1) * pageSize) + 1;
        currentShowToEntries = ((this.currentPage - 1) * pageSize) + items.size();
    }
}