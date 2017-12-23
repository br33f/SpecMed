package com.i4m1s1.specmed.dto;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class BindUserToCustomerDTO {
    private String customerId;
    private String userEmail;
    private String userPassword;
    private String pesel;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
