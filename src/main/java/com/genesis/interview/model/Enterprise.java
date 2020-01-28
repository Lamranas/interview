package com.genesis.interview.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Enterprise {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne
    private Address headQuarter;
    @OneToMany
    private List<Address> addresses;
    @ManyToMany
    private List<Contact> contacts;
    private Vat vat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getHeadQuarter() {
        return headQuarter;
    }

    public void setHeadQuarter(Address headQuarter) {
        this.headQuarter = headQuarter;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Vat getVat() {
        return vat;
    }

    public void setVat(Vat vat) {
        this.vat = vat;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
