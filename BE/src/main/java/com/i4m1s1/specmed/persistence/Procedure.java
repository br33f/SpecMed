package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.annotation.Related;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 *
 * Encja zawierająca dane zabiegu
 * TO JEDNA Z ENCJI SLUŻĄCA DO GENEROWANIA HARMONOGRAMU LEKARZA

 */
public class Procedure {
    @Id
    private String id;
    @Related
    private List<MedicalEmployee> medicalEmployeeList; // lista lekarzy biorących udział w zabiegu
    @Related
    private Customer customer;
    private String price;
    private long date;
    @DBRef
    private List<Probe> probes; // Jakieś poprzednie badania z ich wynikami przydatnymi do zabiegu - zeby czyms sie roznilo

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MedicalEmployee> getMedicalEmployeeList() {
        return medicalEmployeeList;
    }

    public void setMedicalEmployeeList(List<MedicalEmployee> medicalEmployeeList) {
        this.medicalEmployeeList = medicalEmployeeList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
