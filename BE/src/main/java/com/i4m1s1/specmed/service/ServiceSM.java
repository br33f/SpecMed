package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.ResponseSM;
import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.persistence.Visit;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public interface ServiceSM<Response, Request> {
    Response provide(Request request) throws SMException;
}
