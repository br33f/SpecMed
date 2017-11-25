package com.i4m1s1.specmed.service.response.common;

import com.i4m1s1.specmed.service.response.ServiceResponse;
import org.springframework.data.domain.Page;

/**
 * Created by br33 on 25.11.2017.
 */
public class ListResponse implements ServiceResponse {
    private long totalCount;
    private long totalPages;
    private Object data;

    public ListResponse() {
    }

    public ListResponse(int totalCount, int totalPages, Object data) {
        this.totalCount = totalCount;
        this.data = data;
    }

    public ListResponse(Page page) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
