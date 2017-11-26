package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.annotation.Dictionary;
import com.i4m1s1.specmed.core.annotation.Related;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;


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
    @Related
    private MedicalEmployee medicalEmpoyee; //w jpa tak sie robi. ulatwia selecty
    @Related
    private Customer customer;
    private String price;
    @Dictionary(DictionaryNames.VISIT_STATUS)
    private String status; //wolna, zamowiona, odbyta, zaplacona(?)
    private String place; //zmienic na slownik czy co tu ma w ogole byc todo?
    private long date;

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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public MedicalEmployee getMedicalEmpoyee() {
        return medicalEmpoyee;
    }

    public void setMedicalEmpoyee(MedicalEmployee medicalEmpoyee) {
        this.medicalEmpoyee = medicalEmpoyee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
