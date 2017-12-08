package com.i4m1s1.specmed.service.common.response;

/**
 * Klasa dla wszystkich Response w projekcie
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
public abstract class ApiResponse {
    private String error;
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
