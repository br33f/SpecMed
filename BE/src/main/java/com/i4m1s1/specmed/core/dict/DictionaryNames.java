package com.i4m1s1.specmed.core.dict;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public enum DictionaryNames {
    GENDER(1, "GENDER"),
    SPECIALIZATION(2, "SPECIALIZATION"),
    VISIT_STATUS(3, "VISIT_STATUS"),
    MEDICAL_SPECIALISATION(4,"MEDICAL_SPECIALISATION");

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
