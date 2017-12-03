package com.i4m1s1.specmed.service.response;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class BasicResponse<T> extends ApiResponse {
    protected T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
