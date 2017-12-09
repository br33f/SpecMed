package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.annotation.Dictionary;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import org.springframework.data.annotation.Id;

/**
 * Encja zawierająca dane pojedynczego badaia
 * Czyli np. badanie prostaty we wtorek, które sobie kupił jakiś konkretny {@link Customer}
 * TO JEDNA Z ENCJI SLUŻĄCA DO GENEROWANIA HARMONOGRAMU LEKARZA
 */
public class Probe {
    @Id
    private String id;
    @Dictionary(DictionaryNames.PROBES)
    private String type;
    private MedicalEmployee medicalEmployee;
    private Customer customer;
    private long date;

    private String price; //cena - moze nie byc wymagana
    /** Jakies wyniki badania. Normalnie powinny to być dokumenty ale nie mamy tyle czasu.
     wiec sam komentarz: "Udalo sie badanie. U pacjenta stwierdzono raka" */
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public MedicalEmployee getMedicalEmployee() {
        return medicalEmployee;
    }

    public void setMedicalEmployee(MedicalEmployee medicalEmployee) {
        this.medicalEmployee = medicalEmployee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
