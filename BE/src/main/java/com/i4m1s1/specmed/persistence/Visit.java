package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.annotation.Dictionary;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import org.springframework.data.annotation.Id;


/**
 * ONE-TO-MANY
 * https://stackoverflow.com/questions/29303916/spring-data-mongodb-how-to-implement-entity-relationships
 * <p>
 * The mapping framework does not handle cascading saves.
 * If you change an Account object that is referenced by a Person object, you must save the Account object separately.
 * Calling save on the Person object will not automatically save the Account objects in the property accounts.
 *
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */

public class Visit {
    /**
     * Mozna zrobic tak zeby wizyta istniala zawsze. Tzn Przed jej zamowieniem tez.
     * Najpierw tworzona jest wizyta (dodawana do listy) dla lekarza. Nie posiada ona klienta.
     * <p>
     * Podczas rezerwacji wizyty pacjent przegląda WSZYSTKIE WOLNE (bez klientow/ze statusem wolne)
     * wizyty z interesującymi go parametrami (np. wizyty u proktologów lub itp.)
     * Wydaje sie to byc najprostsze w implementacji i generujace najmniej bledow.
     */

    @Id
    private String id;
    private String price;
    @Dictionary(DictionaryNames.VISIT_STATUS)
    private String status; //wolna, zamowiona, odbyta, zaplacona(?)
    private String day; //format 20171124 - najprostsze w impl. inne(?)

    private String hourStart;
    private String hourEnd;
    private String place; //zmienic na slownik czy co tu ma w ogole byc todo?

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHourStart() {
        return hourStart;
    }

    public void setHourStart(String hourStart) {
        this.hourStart = hourStart;
    }

    public String getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(String hourEnd) {
        this.hourEnd = hourEnd;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
