package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.PrescriptionRow;
import com.i4m1s1.specmed.core.annotation.Dictionary;
import com.i4m1s1.specmed.core.annotation.Related;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Encja zawierająca dane recept
 */
public class Prescription {
    @Id
    private String id;
    private Customer customer;
    @Dictionary(DictionaryNames.NFZ_UNIT)
    private String NFZunit;
    @Related
    private MedicalEmployee medicalEmployee; //lekarz wystawiający
    private List<PrescriptionRow> rows; //pojedynczy lek
    private long creationDate;
    private long validDate; //termin ważności
    private String discount; // zniżka

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getNFZunit() {
        return NFZunit;
    }

    public void setNFZunit(String NFZunit) {
        this.NFZunit = NFZunit;
    }

    public MedicalEmployee getMedicalEmployee() {
        return medicalEmployee;
    }

    public void setMedicalEmployee(MedicalEmployee medicalEmployee) {
        this.medicalEmployee = medicalEmployee;
    }

    public List<PrescriptionRow> getRows() {
        return rows;
    }

    public void setRows(List<PrescriptionRow> rows) {
        this.rows = rows;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getValidDate() {
        return validDate;
    }

    public void setValidDate(long validDate) {
        this.validDate = validDate;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
