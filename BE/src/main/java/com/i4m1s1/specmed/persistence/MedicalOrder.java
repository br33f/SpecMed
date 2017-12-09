package com.i4m1s1.specmed.persistence;

import com.i4m1s1.specmed.core.annotation.Dictionary;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Encja zawierająca dane usługi medycznej
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */

public class MedicalOrder <T>{

    @Id
    private String id;
    @DBRef
    private MedicalEmployee employeeReporter; //osoba zgłaszająca zlecenie
    @Dictionary(DictionaryNames.ORDER_TYPE) //wartosci PROBES albo MEDICAL_PROCEDURES
    private String type;
    private T action; //probe albo procedure

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MedicalEmployee getEmployeeReporter() {
        return employeeReporter;
    }

    public void setEmployeeReporter(MedicalEmployee employeeReporter) {
        this.employeeReporter = employeeReporter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getAction() {
        return action;
    }

    public void setAction(T action) {
        this.action = action;
    }
}
