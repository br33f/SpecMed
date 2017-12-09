package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.AddressData;
import com.i4m1s1.specmed.core.ContactData;
import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.core.annotation.Dictionary;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Encja zawierająca dane pracownika medycznego
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
public class MedicalEmployee {

    @Id
    private String id;
    private PersonalData personalData;
    private AddressData addressData;
    private ContactData contactData;
    @Dictionary(DictionaryNames.SPECIALIZATION)
    private List<String> specializationList;

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public List<String> getSpecializationList() {
        return specializationList;
    }

    public void setSpecializationList(List<String> specializationList) {
        this.specializationList = specializationList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
