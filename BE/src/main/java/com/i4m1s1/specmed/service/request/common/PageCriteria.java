package com.i4m1s1.specmed.service.request.common;

/**
 * Created by br33 on 25.11.2017.
 */
public class PageCriteria {
    private int offset;
    private int pageSize;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
