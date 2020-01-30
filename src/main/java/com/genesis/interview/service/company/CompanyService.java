package com.genesis.interview.service.company;

import com.genesis.interview.model.Address;
import com.genesis.interview.model.Company;
import com.genesis.interview.model.Contact;
import com.genesis.interview.service.CrudService;

public interface CompanyService extends CrudService<Company> {

    void defineHeadquarter(Long id, Long addressId);

    void addAddress(Long id, Address address);

    void updateAddress(Long id, Address address);

    void addContact(Long id, Contact contact);

    void removeContact(Long companyId, Long contactId);
}
