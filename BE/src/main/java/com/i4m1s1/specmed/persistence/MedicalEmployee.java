package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.annotation.Dictionary;
import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * Encja zawierająca dane pracownika medycznego
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class MedicalEmployee {

    @Id
    private String id;
    private PersonalData personalData;
    @Dictionary(DictionaryNames.SPECIALIZATION)
    private List<String> specializationList;
    /**
     * One-To-Many
     * https://docs.spring.io/spring-data/mongodb/docs/1.3.3.RELEASE/reference/html/mapping-chapter.html
     * search for DBRef
     * KAZDY OBIEKT OZNACZONY DBREF MUSI POSIADAC WLASNE REPOSITORY I DO NIEGO SAVEUJEMY DANE!
     * OBIEKTY OZNACZONE DBREF NIE PODPADAJĄ POD CASCADE
     */
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

}
