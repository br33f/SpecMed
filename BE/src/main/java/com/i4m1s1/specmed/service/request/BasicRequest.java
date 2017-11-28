package com.i4m1s1.specmed.service.request;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class BasicRequest<T> extends ApiRequest {

    protected T chunkData;

    public T getChunkData() {
        return chunkData;
    }

    public void setChunkData(T chunkData) {
        this.chunkData = chunkData;
    }
}
