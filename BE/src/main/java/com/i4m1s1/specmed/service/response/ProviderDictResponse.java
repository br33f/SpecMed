package com.i4m1s1.specmed.service.response;

import java.util.Map;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class ProviderDictResponse implements ServiceResponse {
    private Map<Integer, String> dict;

    public ProviderDictResponse(Map<Integer, String> dict) {
        this.dict = dict;
    }

    public Map<Integer, String> getDict() {
        return dict;
    }

    public void setDict(Map<Integer, String> dict) {
        this.dict = dict;
    }
}
