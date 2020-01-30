package com.genesis.interview.service.company;

import com.genesis.interview.exception.NoEntityFound;
import com.genesis.interview.model.Address;
import com.genesis.interview.model.Company;
import com.genesis.interview.model.Contact;
import com.genesis.interview.repository.CompanyRepository;
import com.genesis.interview.service.address.AddressService;
import com.genesis.interview.service.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ContactService contactService;

    @Override
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company getById(Long id) {
        Objects.requireNonNull(id,"Id should not be null");
        Optional<Company> optionalPersistedCompany = companyRepository.findById(id);

        return optionalPersistedCompany.orElseThrow(() -> new NoEntityFound("No company found with id: "+id));
    }

    @Override
    @Transactional
    public Company create(Company company) {
        Objects.requireNonNull(company,"The company should not be null");

        if(company.getHeadQuarter() != null) {
            Address address = addressService.create(company.getHeadQuarter());
            company.setHeadQuarter(address);
        }

        return companyRepository.save(company);
    }

    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id,"Id should not be null");
        Company company = getById(id);
        companyRepository.delete(company);
    }

    @Override
    public Company update(Long id, Company company) {
        Objects.requireNonNull(id,"Id should not be null");
        Objects.requireNonNull(company,"The company should not be null");

        Company persistedCompany = getById(id);
        persistedCompany.setName(company.getName());
        persistedCompany.setVat(company.getVat());
        persistedCompany.setAddresses(company.getAddresses());
        persistedCompany.setContacts(company.getContacts());
        persistedCompany.setHeadQuarter(company.getHeadQuarter());

        return companyRepository.save(persistedCompany);
    }

    @Override
    public void defineHeadquarter(Long id, Long addressId) {
        Objects.requireNonNull(id,"Id should not be null");
        Objects.requireNonNull(addressId,"addressId should not be null");

        Address address = addressService.getById(addressId);
        Company company = getById(id);

        // Check first if the address id is in the addesses list
        boolean futureHeadQuarterInList = isAddressPresent(company, addressId);

        if(futureHeadQuarterInList) { // then we could set it as the head quarter
            company.setHeadQuarter(address);
            companyRepository.save(company);
        } else { // if not, we have to throw an error
            throw new IllegalArgumentException("This address is not in the addresses list ");
        }
    }

    @Override
    public void addAddress(Long id, Address address) {
        Objects.requireNonNull(id,"Id should not be null");
        Objects.requireNonNull(address,"address should not be null");

        Company company = getById(id);
        company.getAddresses().add(address);

        companyRepository.save(company);
    }

    @Override
    public void updateAddress(Long id, Address address) {
        Objects.requireNonNull(id,"Id should not be null");
        Objects.requireNonNull(address,"address should not be null");

        Company company = getById(id);
        if(isAddressPresent(company, address.getId())) {
            addressService.update(address.getId(), address );
        }else {
            throw new IllegalArgumentException("This address is not one of this company addresses");
        }
    }

    @Override
    @Transactional
    public void addContact(Long id, Contact contact) {
        Objects.requireNonNull(id,"Id should not be null");
        Objects.requireNonNull(contact,"contact should not be null");

        Company company = getById(id);
        company.getContacts().add(contact);

        companyRepository.save(company);
    }

    @Override
    public void removeContact(Long companyId, Long contactId) {
        Objects.requireNonNull(companyId,"companyId should not be null");
        Objects.requireNonNull(contactId,"contactId should not be null");

        Company company = getById(companyId);
        company.getContacts().removeIf(c->c.getId().equals(contactId));

        companyRepository.save(company);
    }

    private boolean isAddressPresent(Company company, Long addressId) {
        return company.getAddresses()
                .stream()
                .anyMatch(a -> a.getId().equals(addressId));
    }
}
