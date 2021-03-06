package ua.com.alevel.persistence.datatable;

import java.util.HashMap;
import java.util.Map;

public class DataTableRequest {

    private String sort;
    private String order;
    private int currentPage;
    private int pageSize;
    private Map<String, Object> queryMap;

    public DataTableRequest() {
        this.queryMap = new HashMap<>();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getQueryMap() {
        return queryMap;
    }

    public void setQueryMap(Map<String, Object> queryMap) {
        this.queryMap = queryMap;
    }
}