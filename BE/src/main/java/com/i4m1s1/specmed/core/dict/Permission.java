package com.i4m1s1.specmed.core.dict;

/**
 * @author br33f
 */
public enum Permission {
    CUSTOMER("CUSTOMER"),
    EMPLOYEE("EMPLOYEE"),
    MEDICAL_EMPLOYEE("MEDICAL_EMPLOYEE"),
    DIRECTOR("DIRECTOR");

    private final String code;

    Permission(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }
}
