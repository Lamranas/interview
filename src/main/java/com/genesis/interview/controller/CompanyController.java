package com.genesis.interview.controller;

import com.genesis.interview.model.Address;
import com.genesis.interview.model.Company;
import com.genesis.interview.model.Contact;
import com.genesis.interview.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/company")
public class CompanyController extends CrudController<Company>{


    @Autowired
    protected CompanyController(CompanyService companyService) {
        super(companyService);
    }

    @PatchMapping("/{companyId}/address")
    public void addAddress(@PathVariable Long companyId, @RequestBody Address address) {
        ((CompanyService)service).addAddress(companyId,address);
    }

    @PatchMapping("/{companyId}/headquarter/{addressId}")
    public void defineHeadQuarter(@PathVariable Long companyId, @PathVariable Long addressId) {
        ((CompanyService)service).defineHeadquarter(companyId,addressId);
    }

    @PatchMapping("/{companyId}/address/{addressId}")
    public void updateAddress(@PathVariable Long companyId, @RequestBody Address address) {
        ((CompanyService)service).updateAddress(companyId,address);
    }

    @PostMapping("/{companyId}/contact")
    public void addContact(@PathVariable Long companyId, @RequestBody Contact contact) {
        ((CompanyService)service).addContact(companyId,contact);
    }


    @DeleteMapping("/{companyId}/contact/{contactId}")
    public void removeContact(@PathVariable Long companyId, @PathVariable Long contactId) {
        ((CompanyService)service).removeContact(companyId,contactId);
    }
}
