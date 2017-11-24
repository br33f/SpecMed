package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.SMException;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public interface ServiceSimpleSM <Response>{
    Response provide() throws SMException;
}
