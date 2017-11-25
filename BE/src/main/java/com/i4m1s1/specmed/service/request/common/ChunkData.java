package com.i4m1s1.specmed.service.request.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Map;

/**
 * Created by br33 on 25.11.2017.
 */
public class ChunkData extends ObjectNode {
    public ChunkData(JsonNodeFactory nc) {
        super(nc);
    }

    public ChunkData(JsonNodeFactory nc, Map<String, JsonNode> kids) {
        super(nc, kids);
    }
}
