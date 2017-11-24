package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.core.enums.Specialization;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class MedicalEmployee {

    @Id
    private String id;
    private PersonalData personalData;
    private List<Specialization> specializationList;
    /**
     * One-To-Many
     * https://docs.spring.io/spring-data/mongodb/docs/1.3.3.RELEASE/reference/html/mapping-chapter.html
     * search for DBRef
     * KAZDY OBIEKT OZNACZONY DBREF MUSI POSIADAC WLASNE REPOSITORY I DO NIEGO SAVEUJEMY DANE!
     * OBIEKTY OZNACZONE DBREF NIE PODPADAJĄ POD CASCADE
     */
    @DBRef
    private List<Visit> visits;

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

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
