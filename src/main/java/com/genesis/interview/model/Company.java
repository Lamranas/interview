package com.genesis.interview.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(orphanRemoval=true)
    private Address headQuarter;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "COMPANY_ADDRESS",
            joinColumns = @JoinColumn(name = "COMPANY"),
            inverseJoinColumns = @JoinColumn(name = "ADDRESS")
    )
    private List<Address> addresses;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "JOB",
            joinColumns = @JoinColumn(name = "COMPANY"),
            inverseJoinColumns = @JoinColumn(name = "CONTACT")
    )
    private List<Contact> contacts;

    private String vat;

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
        return addresses != null ? addresses : new ArrayList();
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


    public List<Contact> getContacts() {
        return contacts != null ? contacts : new ArrayList();
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

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }
}
