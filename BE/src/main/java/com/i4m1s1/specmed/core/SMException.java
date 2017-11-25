package com.i4m1s1.specmed.core;

import com.i4m1s1.specmed.core.dict.WarningMsg;

import java.util.Arrays;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class SMException extends Exception {

    private SMException(String message) {
        super(message);
    }

    public SMException(String unique, WarningMsg warningMsg) {
        super(unique + ":" + warningMsg.message());
    }

    public SMException(String unique, WarningMsg warningMsg, String... params) {
        super(unique + ":" + warningMsg.message() + ":" + Arrays.deepToString(params));
    }
}
