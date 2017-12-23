package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.dict.Permission;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

/**
 * Encja reprezentujaca użytkownika
 * @author br33f
 */
public class User {

    @Id
    private String id;
    private String email;
    private String password;
    private String entityId;
    private ArrayList<Permission> permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}
