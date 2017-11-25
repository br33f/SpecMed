package com.i4m1s1.specmed.core;

import com.i4m1s1.specmed.core.enums.WarningMsg;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class SMException extends Exception {

    public SMException(String unique, WarningMsg warningMsg) {
        super(unique + ":" + warningMsg.message());
    }
}
