package com.i4m1s1.specmed.service.common.response;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Klasa dla odpowiedzi dotyczących List przez API
 * Created by br33 on 25.11.2017.
 */
public class ListResponse<T> extends ApiResponse {
    private long totalCount;
    private long totalPages;
    private List<T> content;

    public ListResponse() {
    }

    public ListResponse(long totalCount, long totalPages, List<T> content) {
        this.totalCount = totalCount;
        this.content = content;
    }

    public ListResponse(Page<T> page) {
        this.totalCount = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.content = page.getContent();
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

    public List<T> getContent() {
        return content;
    }

    public void getContent(List<T> content) {
        this.content = content;
    }
}
