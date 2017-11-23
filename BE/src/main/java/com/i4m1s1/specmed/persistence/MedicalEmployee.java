package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.core.enums.Specialization;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class MedicalEmployee {

    @Id
    private String id;
    private PersonalData personalData;
    private List<Specialization> specializationList;

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public List<Specialization> getSpecializationList() {
        return specializationList;
    }

    public void setSpecializationList(List<Specialization> specializationList) {
        this.specializationList = specializationList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
