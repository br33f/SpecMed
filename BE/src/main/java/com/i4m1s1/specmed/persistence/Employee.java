package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.AddressData;
import com.i4m1s1.specmed.core.ContactData;
import com.i4m1s1.specmed.core.PersonalData;
import org.springframework.data.annotation.Id;

/**
 * Encja zawierająca dane pracownika
 */
public class Employee {
    @Id
    private String id;
    private PersonalData personalData;
    private AddressData addressData;
    private ContactData contactData;

    public Employee() {
    }

    public Employee(PersonalData personalData) {
        this.personalData = personalData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
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
}
