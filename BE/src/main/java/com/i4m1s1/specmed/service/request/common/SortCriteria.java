package com.i4m1s1.specmed.service.request.common;

/**
 * Created by br33 on 25.11.2017.
 */
public class SortCriteria {
    private String sortKey;
    private String sortOrder;

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
