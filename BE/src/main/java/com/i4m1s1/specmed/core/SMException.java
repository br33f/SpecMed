package com.i4m1s1.specmed.core;

import com.i4m1s1.specmed.core.enums.WarningMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class SMException extends Exception {

    public SMException(String unique, WarningMsg warningMsg) {
        super(unique + ":" + warningMsg.message());
    }
}
