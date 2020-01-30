package com.genesis.interview.controller;

import com.genesis.interview.model.Contact;
import com.genesis.interview.service.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController extends CrudController<Contact> {

    @Autowired
    public ContactController(ContactService contactService){
        super(contactService);
    }

}
