package com.mobileclient.domain;

import java.io.Serializable;

public class Company implements Serializable {
    /*��˾id*/
    private int companyId;
    public int getCompanyId() {
        return companyId;
    }
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /*��˾����*/
    private String companyName;
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}