package com.genesis.interview.model;

import javax.persistence.Entity;

@Entity
public class Freelance extends Contact {

    private String vat;

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }
}
