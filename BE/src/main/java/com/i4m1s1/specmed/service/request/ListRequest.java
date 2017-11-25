package com.i4m1s1.specmed.service.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.i4m1s1.specmed.service.request.common.PageCriteria;

/**
 * Created by br33 on 25.11.2017.
 */
public class ListRequest extends BasicRequest {
    private PageCriteria pageCriteria;
    private JsonNode searchCriteria;

    public PageCriteria getPageCriteria() {
        return pageCriteria;
    }

    public void setPageCriteria(PageCriteria pageCriteria) {
        this.pageCriteria = pageCriteria;
    }

    public JsonNode getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(JsonNode searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
