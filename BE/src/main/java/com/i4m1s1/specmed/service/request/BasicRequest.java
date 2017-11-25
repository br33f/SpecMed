package com.i4m1s1.specmed.service.request;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class BasicRequest {
    private JsonNode chunkData;

    public JsonNode getChunkData() {
        return chunkData;
    }

    public void setChunkData(JsonNode chunkData) {
        this.chunkData = chunkData;
    }
}
