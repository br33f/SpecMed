package com.i4m1s1.specmed.service.request.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Created by br33 on 25.11.2017.
 */
public class PageCriteria {
    private int currentPage;
    private int pageSize;
    private String sortKey;
    private Sort.Direction sortDirection;

    public PageCriteria() {
        this.sortDirection = Sort.Direction.ASC;
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

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }

    public PageRequest getAsPageRequest() {
        if (getSortKey() != null) {
            Sort sort = new Sort(getSortDirection(), getSortKey());
            return new PageRequest(getCurrentPage(), getPageSize(), sort);
        } else {
            return new PageRequest(getCurrentPage(), getPageSize());
        }
    }
}
