package com.genesis.interview.model;

import javax.persistence.*;
import java.util.List;

@Entity
public abstract class Contact {

    @Id
    @GeneratedValue
    protected Long id;
    protected String firstName;
    protected String lastName;
    @OneToOne
    protected Address address;
    @OneToMany
    protected List<Enterprise> jobs;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Enterprise> getJobs() {
        return jobs;
    }

    public void setJobs(List<Enterprise> jobs) {
        this.jobs = jobs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
