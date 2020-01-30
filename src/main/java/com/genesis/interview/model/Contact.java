package com.genesis.interview.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Freelance.class, name = "freelance"),
        @JsonSubTypes.Type(value = Employee.class, name = "employee")
})
@DiscriminatorColumn(name = "TYPE_CONTACT")
public abstract class Contact {

    @Id
    @GeneratedValue
    protected Long id;
    protected String firstName;
    protected String lastName;
    @OneToOne(orphanRemoval=true, cascade = CascadeType.ALL)
    protected Address address;

    @ManyToMany(mappedBy = "contacts", cascade=CascadeType.ALL)
    protected List<Company> jobs;

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

    public List<Company> getJobs() {
        return jobs != null ? jobs : new ArrayList();
    }

    public void setJobs(List<Company> jobs) {
        this.jobs = jobs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
