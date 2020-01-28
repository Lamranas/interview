package com.genesis.interview.service.contact;

import com.genesis.interview.model.Contact;

public interface ContactService {

    Contact getById(Long id);

    Contact createContact(Contact contact);

    Contact deleteContact(Long id);

    Contact updateContact(Long id, Contact contact);
}
