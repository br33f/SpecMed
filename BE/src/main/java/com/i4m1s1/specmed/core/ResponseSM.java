package com.i4m1s1.specmed.core;

import com.i4m1s1.specmed.core.helper.DateHelper;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class ResponseSM {
    private String error;
    private String time; //kwestia jak wysylac date. String/Date? TODO
    private Object JSONContent;

    private ResponseSM(String error, String time, Object JSONContent) {
        this.error = error;
        this.time = time;
        this.JSONContent = JSONContent;
    }

    public static ResponseSM wrap(Object content, String error, String time) {
        return new ResponseSM(error, time, content);
    }

    public static ResponseSM wrap(Object content, String errors) {
        String time = DateHelper.getCurrentDateAsString("yyyy.MM.dd.HH.mm.ss");
        return new ResponseSM(errors, time, content);
    }

    public String getErrors() {
        return error;
    }

    public void setErrors(String errors) {
        this.error = error;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getJSONContent() {
        return JSONContent;
    }

    public void setJSONContent(Object JSONContent) {
        this.JSONContent = JSONContent;
    }
}
