package com.i4m1s1.specmed.core.dict;

/**
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
//todo przydaloby sie gdzies tego uzywac w appce :/
public enum WarningMsg {
    DB_SAVE_ERROR(1, "Błąd zapisu do bazy danych"),
    AUTH_ERROR(2, "Błąd autoryzacji"),
    DB_NO_RESULTS(3, "Brak wynikow zapytania"),
    DICTIONARY_NOT_FOUND(4, "Brak slownika!"),
    VISIT_FOR_OPINION_NOT_FOUND(5, "Brak wizyty o podanym ID. Nie można dodać wizyty!"),
    CANT_BIND_WRONG_PASSWORD(6, "Podano złe hasło"),
    CANT_BIND_WRONG_DATA(7, "Podane złe dane"),
    GENERIC_VALIDATE(8, "Błąd walidacji żądania"),
    MUST_LOGGED(9, "Wymagany zalogowany użytkownik"),
    NOT_ENOUGH_PARAMS(10, "Nie przekazano wystarczających parametrów");

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
