package com.i4m1s1.specmed.service.response.common;

import com.i4m1s1.specmed.service.response.ServiceResponse;

/**
 * Created by br33 on 25.11.2017.
 */
public class ListResponse implements ServiceResponse {
    private int totalCount;
    private Object data;

    public ListResponse() {
    }

    public ListResponse(int totalCount, Object data) {
        this.totalCount = totalCount;
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
