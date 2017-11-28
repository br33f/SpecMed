package com.i4m1s1.specmed.service.response;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by br33 on 25.11.2017.
 */
public class ListResponse<T> extends ApiResponse {
    private long totalCount;
    private long totalPages;
    private List<T> data;

    public ListResponse() {
    }

    public ListResponse(long totalCount, long totalPages, List<T> data) {
        this.totalCount = totalCount;
        this.data = data;
    }

    public ListResponse(Page<T> page) {
        this.totalCount = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.data = page.getContent();
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
