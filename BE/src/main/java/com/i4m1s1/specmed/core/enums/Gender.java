package com.i4m1s1.specmed.core.enums;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public enum Gender {
    MALE(1, "M", ""),
    FEMALE(2, "F", ""),
    UNSPECIFIED(3, "U", "");

    private final int id;
    private final String code;
    private final String desc;


    Gender(int id, String code, String desc) {
        this.id = id;
        this.code = code;
        this.desc = desc;
    }

    public int id() {
        return this.id;
    }

    public String code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }
}
