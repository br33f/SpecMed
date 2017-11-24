package com.i4m1s1.specmed.core.enums;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public enum DictionaryNames {
    GENDER(1, "GENDER"),
    SPECIALIZATION(2, "SPECIALIZATION"),
    VISIT_STATUS(3, "VISIT_STATUS");

    private final int id;
    private final String code;


    DictionaryNames(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int id() {
        return this.id;
    }

    public String code() {
        return this.code;
    }
}
