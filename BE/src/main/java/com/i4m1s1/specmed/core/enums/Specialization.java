package com.i4m1s1.specmed.core.enums;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public enum Specialization {

    ONKOLOG(1, "ONKOLOG"),
    PEDIATRA(2, "PEDIATRA");

    private final int id;
    private final String desc;


    Specialization(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int id() {
        return this.id;
    }

    public String desc() {
        return this.desc;
    }

    public Specialization findByName(String find) {
        for (Specialization s : Specialization.values()) {
            if (find.equals(s.name())) {
                return s;
            }
        }
        return null;
    }
}
