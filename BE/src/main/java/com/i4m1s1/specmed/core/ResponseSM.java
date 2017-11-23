package com.i4m1s1.specmed.core;

import com.i4m1s1.specmed.core.helper.DateHelper;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class ResponseSM {
    private List<String> errors;
    private String time; //kwestia jak wysylac date. String/Date? TODO
    private Object JSONContent;

    private ResponseSM(List<String> errors, String time, Object JSONContent) {
        this.errors = errors;
        this.time = time;
        this.JSONContent = JSONContent;
    }

    public static ResponseSM wrap(Object content, List<String> errors, String time) {
        return new ResponseSM(errors, time, content);
    }

    public static ResponseSM wrap(Object content, List<String> errors) {
        String time = DateHelper.getCurrentDateAsString("yyyy.MM.dd.HH.mm.ss");
        return new ResponseSM(errors, time, content);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
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
