package com.genesis.interview.service.contact;

import com.genesis.interview.exception.NoEntityFound;
import com.genesis.interview.model.Address;
import com.genesis.interview.model.Contact;
import com.genesis.interview.model.Freelance;
import com.genesis.interview.repository.ContactRepository;
import com.genesis.interview.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressService addressService;

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getById(Long id) {
        Objects.requireNonNull(id,"Id should not be null");
        Optional<Contact> optionalPersistedContact = contactRepository.findById(id);

        return optionalPersistedContact.orElseThrow(() -> new NoEntityFound("No contact found with id: "+id));
    }

    @Override
    @Transactional
    public Contact create(Contact contact) {
        Objects.requireNonNull(contact,"The contact should not be null");

        if(contact.getAddress() != null) {
            Address address = addressService.create(contact.getAddress());
            contact.setAddress(address);
        }

        return contactRepository.save(contact);
    }

    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id,"Id should not be null");
        Contact contact = getById(id);
        contactRepository.delete(contact);
    }

    @Override
    public Contact update(Long id, Contact contact) {
        Objects.requireNonNull(id,"Id should not be null");
        Objects.requireNonNull(contact,"The contact should not be null");

        Contact persistedContact = getById(id);
        persistedContact.setAddress(contact.getAddress());
        persistedContact.setFirstName(contact.getFirstName());
        persistedContact.setLastName(contact.getLastName());
        persistedContact.setJobs(contact.getJobs());

        if(contact instanceof Freelance) {
            ((Freelance)persistedContact).setVat(((Freelance) contact).getVat());
        }

        return contactRepository.save(persistedContact);
    }
}
