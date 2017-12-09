package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.AddressData;
import com.i4m1s1.specmed.core.ContactData;
import com.i4m1s1.specmed.core.annotation.Related;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Encja zawierająca dane placówek
 */
public class Unit {
    @Id
    private String id;
    private String name;
    private AddressData addressData;
    private ContactData contactData;
    @Related
    private List<Employee> employees;
    @Related
    private List<MedicalEmployee> medicalEmployees;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressData getAddressData() {
        return addressData;
    }

    public void setAddressData(AddressData addressData) {
        this.addressData = addressData;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public void setContactData(ContactData contactData) {
        this.contactData = contactData;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<MedicalEmployee> getMedicalEmployees() {
        return medicalEmployees;
    }

    public void setMedicalEmployees(List<MedicalEmployee> medicalEmployees) {
        this.medicalEmployees = medicalEmployees;
    }
}
