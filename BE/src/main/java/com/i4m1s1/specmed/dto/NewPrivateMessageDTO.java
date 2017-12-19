package com.i4m1s1.specmed.dto;

import com.i4m1s1.specmed.persistence.PrivateMessage;
import org.springframework.data.annotation.Id;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class NewPrivateMessageDTO {

    private String content;
    private String medicalEmployeeId;
    private String customerId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMedicalEmployeeId() {
        return medicalEmployeeId;
    }

    public void setMedicalEmployeeId(String medicalEmployeeId) {
        this.medicalEmployeeId = medicalEmployeeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
