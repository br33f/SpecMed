package com.i4m1s1.specmed.service.response;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class ProviderDictResponse implements ServiceResponse {
    private List<String> dict;

    public ProviderDictResponse(List<String> dict) {
        this.dict = dict;
    }

    public List<String> getDict() {
        return dict;
    }

    public void setDict(List<String> dict) {
        this.dict = dict;
    }
}
