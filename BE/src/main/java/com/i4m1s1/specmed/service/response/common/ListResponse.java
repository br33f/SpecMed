package com.i4m1s1.specmed.service.response.common;

import com.i4m1s1.specmed.service.response.ServiceResponse;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Generycznie bo latwiej lapac bledy jak ktos pomyli klasy (czasami wywala blad juz w IDE) ~TF
 * Poza tym klasa Page tego wymaga
 * Created by br33 on 25.11.2017.
 */
public class ListResponse<T> implements ServiceResponse {
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
