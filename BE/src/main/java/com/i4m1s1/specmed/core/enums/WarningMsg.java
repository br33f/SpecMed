package com.i4m1s1.specmed.core.enums;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
//todo przydaloby sie gdzies tego uzywac w appce :/
public enum WarningMsg {
    DB_SAVE_ERROR(1, "Błąd zapisu do bazy danych"),
    AUTH_ERROR(2, "Błąd autoryzacji");

    private final int id;
    private final String message;

    WarningMsg(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int id() {
        return this.id;
    }

    public String message() {
        return this.message;
    }
}
