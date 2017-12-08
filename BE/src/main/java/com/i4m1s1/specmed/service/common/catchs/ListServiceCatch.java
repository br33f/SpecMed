package com.i4m1s1.specmed.service.common.catchs;

import com.i4m1s1.specmed.service.common.request.ListRequest;
import com.i4m1s1.specmed.service.common.response.ListResponse;

/**
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
public abstract class ListServiceCatch<ReqContent, ResContent> extends ServiceCatch<ListRequest<ReqContent>, ListResponse<ResContent>> {

    @Override
    protected ListResponse<ResContent> createStubResponse() {
        return new ListResponse<>();
    }
}
