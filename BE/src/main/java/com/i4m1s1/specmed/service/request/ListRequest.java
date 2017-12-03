package com.i4m1s1.specmed.service.request;

import com.i4m1s1.specmed.service.request.common.PageCriteria;

/**
 * Created by br33 on 25.11.2017.
 */
public class ListRequest<T> extends ApiRequest {
    private PageCriteria pageCriteria;
    private T searchCriteria;

    public PageCriteria getPageCriteria() {
        return pageCriteria;
    }

    public void setPageCriteria(PageCriteria pageCriteria) {
        this.pageCriteria = pageCriteria;
    }

    public T getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(T searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
