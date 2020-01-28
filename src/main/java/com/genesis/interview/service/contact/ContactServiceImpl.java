package com.genesis.interview.service.contact;

import com.genesis.interview.model.Contact;
import com.genesis.interview.model.Freelance;
import com.genesis.interview.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact getById(Long id) {
        Objects.requireNonNull(id,"Id should not be null");
        Optional<Contact> optionalPersistedContact = contactRepository.findById(id);

        return optionalPersistedContact.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Contact createContact(Contact contact) {
        Objects.requireNonNull(contact,"The contact should not be null");
        return contactRepository.save(contact);
    }

    @Override
    public Contact deleteContact(Long id) {
        Objects.requireNonNull(id,"Id should not be null");
        Contact contact = getById(id);
        contactRepository.delete(contact);
        return contact;
    }

    @Override
    public Contact updateContact(Long id, Contact contact) {
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
