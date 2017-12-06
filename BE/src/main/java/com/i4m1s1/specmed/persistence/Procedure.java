package com.i4m1s1.specmed.persistence;

import org.springframework.data.annotation.Id;

/**
 * Encja zawierająca dane zabiegu
 */
public class Procedure {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
