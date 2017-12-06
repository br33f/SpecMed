package com.i4m1s1.specmed.persistence;

import org.springframework.data.annotation.Id;

/**
 * Encja zawierająca dane usługi medycznej
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
public class MedicalOrder {

    @Id
    private String id;
    //klient i doktor TODO
}
