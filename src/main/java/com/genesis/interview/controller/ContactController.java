package com.genesis.interview.controller;

import com.genesis.interview.model.Contact;
import com.genesis.interview.service.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Create contact
    @PostMapping
    public Contact create (@RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    // Update contact
    @PutMapping("/{id}")
    public Contact update ( @PathVariable Long id, @RequestBody Contact contact) {
        return contactService.updateContact(id, contact);
    }

    // Delete contact
    @DeleteMapping("/{id}")
    public Contact delete (@PathVariable Long id) {
        return contactService.deleteContact(id);
    }

}
