package com.i4m1s1.specmed.dto;

import com.i4m1s1.specmed.core.PrescriptionRow;
import com.i4m1s1.specmed.core.annotation.Dictionary;
import com.i4m1s1.specmed.core.dict.DictionaryNames;

import java.util.List;

/**
 * Data transfer object dla recepty
 * @author br33f
 */

public class PrescriptionDTO {
    private String customerId;
    @Dictionary(DictionaryNames.NFZ_UNIT)
    private String NFZunit;
    private List<PrescriptionRow> rows;
    private long creationDate;
    private long validDate;
    private String discount;


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getNFZunit() {
        return NFZunit;
    }

    public void setNFZunit(String NFZunit) {
        this.NFZunit = NFZunit;
    }

    public List<PrescriptionRow> getRows() {
        return rows;
    }

    public void setRows(List<PrescriptionRow> rows) {
        this.rows = rows;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getValidDate() {
        return validDate;
    }

    public void setValidDate(long validDate) {
        this.validDate = validDate;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
