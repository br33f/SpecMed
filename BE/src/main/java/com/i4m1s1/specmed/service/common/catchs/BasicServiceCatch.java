package com.i4m1s1.specmed.service.common.catchs;

import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;

/**
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
public abstract class BasicServiceCatch<ReqContent, ResContent> extends ServiceCatch<BasicRequest<ReqContent>, BasicResponse<ResContent>> {

    @Override
    protected BasicResponse<ResContent> createStubResponse() {
        return new BasicResponse<>();
    }
}
