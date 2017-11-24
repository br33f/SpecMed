package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.ResponseSM;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public interface ServiceSM<Response, Request> {
    Response provide(Request request);
}
