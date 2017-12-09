package com.i4m1s1.specmed.core.dict;

/**
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
public enum DictionaryNames {
    GENDER(1, "GENDER"),
    SPECIALIZATION(2, "SPECIALIZATION"),
    VISIT_STATUS(3, "VISIT_STATUS"),
    ORDER_TYPE(5, "ORDER_TYPE"),
    PROBES(100, "PROBES"),
    MEDICAL_PROCEDURES(101, "MEDICAL_PROCEDURES"),
    NFZ_UNIT(6,"NFZ_UNIT");

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
