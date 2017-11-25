package com.i4m1s1.specmed.service.request;

import com.i4m1s1.specmed.service.request.common.PageCriteria;
import com.i4m1s1.specmed.service.request.common.SearchCriteria;
import com.i4m1s1.specmed.service.request.common.SortCriteria;

/**
 * Created by br33 on 25.11.2017.
 */
public class ListRequest extends BasicRequest {
    private PageCriteria pageCriteria;
    private SearchCriteria searchCriteria;
    private SortCriteria sortCriteria;

    public PageCriteria getPageCriteria() {
        return pageCriteria;
    }

    public void setPageCriteria(PageCriteria pageCriteria) {
        this.pageCriteria = pageCriteria;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public SortCriteria getSortCriteria() {
        return sortCriteria;
    }

    public void setSortCriteria(SortCriteria sortCriteria) {
        this.sortCriteria = sortCriteria;
    }
}
