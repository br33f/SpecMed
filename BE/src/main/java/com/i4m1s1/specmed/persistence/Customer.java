package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.PersonalData;
import org.springframework.data.annotation.Id;

/**
 * Encja reprezentujaca pacjenta
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class Customer {

    @Id
    private String id;
    private PersonalData personalData;
    private String login;
    private String password;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
