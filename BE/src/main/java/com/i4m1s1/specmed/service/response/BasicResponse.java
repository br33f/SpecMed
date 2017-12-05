package com.i4m1s1.specmed.service.response;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class BasicResponse<T> extends ApiResponse {
    protected T content;
    
    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
