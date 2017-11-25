package com.i4m1s1.specmed.core;

import com.i4m1s1.specmed.core.helper.DateHelper;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class ResponseSM {
    private String error;
    private long time;
    private Object content;

    private ResponseSM(String error, long time, Object content) {
        this.error = error;
        this.time = time;
        this.content = content;
    }

    public static ResponseSM wrap(Object content, String error, long time) {
        return new ResponseSM(error, time, content);
    }

    public static ResponseSM wrap(Object content, String errors) {
        long time = DateHelper.getCurrentDateAsLong();
        return new ResponseSM(errors, time, content);
    }

    public String getErrors() {
        return error;
    }

    public void setErrors(String errors) {
        this.error = error;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
